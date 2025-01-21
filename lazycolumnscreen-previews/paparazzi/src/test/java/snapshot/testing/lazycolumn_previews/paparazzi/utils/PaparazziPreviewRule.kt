package snapshot.testing.lazycolumn_previews.paparazzi.utils

import android.content.res.Configuration
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.Density
import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.android.resources.ScreenRatio
import com.android.resources.ScreenRound
import com.android.resources.ScreenSize
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.DevicePreviewInfoParser
import sergio.sastre.composable.preview.scanner.android.device.domain.Orientation
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation
import kotlin.math.ceil

object PaparazziPreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): Paparazzi {
        val previewPaparazziConfig = preview.getAnnotation<PaparazziConfig>()
        return Paparazzi(
            deviceConfig = getDeviceConfig(
                device = preview.previewInfo.device,
                previewHeightInDp = preview.previewInfo.heightDp,
                previewWidthInDp = preview.previewInfo.widthDp,
            ).copy(
                nightMode = getNightMode(preview.previewInfo.uiMode),
                fontScale = preview.previewInfo.fontScale,
                locale = preview.previewInfo.locale.ifBlank { "en" }
            ),
            supportsRtl = true,
            renderingMode = getRenderingMode(
                renderingMode = previewPaparazziConfig?.renderingMode,
                previewHeightInDp = preview.previewInfo.heightDp,
                previewWidthInDp = preview.previewInfo.widthDp
            ),
            renderExtensions = when (previewPaparazziConfig?.enableAccessibility == true) {
                true -> setOf(AccessibilityRenderExtension())
                false -> emptySet()
            }
        )
    }

    private fun getNightMode(uiMode: Int): NightMode =
        when (uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            true -> NightMode.NIGHT
            false -> NightMode.NOTNIGHT
        }

    private fun getRenderingMode(
        renderingMode: PaparazziConfig.RenderingMode?,
        previewWidthInDp: Int,
        previewHeightInDp: Int,
    ): SessionParams.RenderingMode {
        // Do not shrink if height or width are set, otherwise it throws exception
        val defaultRenderingMode = when (previewHeightInDp > 0 || previewWidthInDp > 0) {
            true -> SessionParams.RenderingMode.FULL_EXPAND
            false -> SessionParams.RenderingMode.SHRINK
        }
        return when (renderingMode) {
            PaparazziConfig.RenderingMode.NORMAL -> SessionParams.RenderingMode.NORMAL
            PaparazziConfig.RenderingMode.V_SCROLL -> SessionParams.RenderingMode.V_SCROLL
            PaparazziConfig.RenderingMode.H_SCROLL -> SessionParams.RenderingMode.H_SCROLL
            PaparazziConfig.RenderingMode.FULL_EXPAND -> SessionParams.RenderingMode.FULL_EXPAND
            PaparazziConfig.RenderingMode.SHRINK -> SessionParams.RenderingMode.SHRINK
            null -> defaultRenderingMode
        }
    }

    private fun getDeviceConfig(
        device: String,
        previewWidthInDp: Int,
        previewHeightInDp: Int
    ): DeviceConfig {
        val parsedDevice = DevicePreviewInfoParser.parse(device)?.inPx() ?: return DeviceConfig()
        val conversionFactor = parsedDevice.densityDpi / 160f
        val previewWidthInPx = ceil(previewWidthInDp * conversionFactor).toInt()
        val previewHeightInPx = ceil(previewHeightInDp * conversionFactor).toInt()

        return DeviceConfig(
            screenHeight = when (previewHeightInPx > 0) {
                true -> previewHeightInPx
                false -> parsedDevice.dimensions.height.toInt()
            },
            screenWidth = when(previewWidthInDp > 0) {
                true -> previewWidthInPx
                false -> parsedDevice.dimensions.width.toInt()
            },
            density = Density(parsedDevice.densityDpi),
            xdpi = parsedDevice.densityDpi,
            ydpi = parsedDevice.densityDpi,
            size = ScreenSize.valueOf(parsedDevice.screenSize.name),
            ratio = ScreenRatio.valueOf(parsedDevice.screenRatio.name),
            screenRound = ScreenRound.valueOf(parsedDevice.shape.name),
            orientation = when (parsedDevice.orientation) {
                Orientation.PORTRAIT -> ScreenOrientation.PORTRAIT
                Orientation.LANDSCAPE -> ScreenOrientation.LANDSCAPE
            }
        )
    }
}