package snapshot.testing.lazycolumn_previews.paparazzi.utils

import android.content.res.Configuration
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.Density
import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.android.resources.ScreenRatio
import com.android.resources.ScreenRound
import com.android.resources.ScreenSize
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.DevicePreviewInfoParser
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation
import kotlin.math.ceil

object PaparazziPreviewRule {

    private const val UNDEFINED_API_LEVEL = -1
    private const val MAX_API_LEVEL = 36

    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): Paparazzi {
        val previewPaparazziConfig = preview.getAnnotation<PaparazziConfig>()
        val previewInfo = preview.previewInfo
        return Paparazzi(
            deviceConfig = getDeviceConfig(
                device = previewInfo.device,
                previewHeightInDp = previewInfo.heightDp,
                previewWidthInDp = previewInfo.widthDp,
            ).copy(
                nightMode = getNightMode(previewInfo.uiMode),
                fontScale = previewInfo.fontScale,
                locale = previewInfo.locale.ifBlank { "en" }
            ),
            supportsRtl = true,
            renderingMode = getRenderingMode(
                renderingMode = previewPaparazziConfig?.renderingMode,
                previewHeightInDp = previewInfo.heightDp,
                previewWidthInDp = previewInfo.widthDp
            ),
            renderExtensions = when (previewPaparazziConfig?.enableAccessibility == true) {
                true -> setOf(AccessibilityRenderExtension())
                false -> emptySet()
            },
            environment = detectEnvironment().copy(
                compileSdkVersion = when (previewInfo.apiLevel == UNDEFINED_API_LEVEL) {
                    true -> MAX_API_LEVEL
                    false -> previewInfo.apiLevel
                }
            )
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
    ): SessionParams.RenderingMode =
        when (renderingMode) {
            PaparazziConfig.RenderingMode.NORMAL -> SessionParams.RenderingMode.NORMAL
            PaparazziConfig.RenderingMode.V_SCROLL -> SessionParams.RenderingMode.V_SCROLL
            PaparazziConfig.RenderingMode.H_SCROLL -> SessionParams.RenderingMode.H_SCROLL
            PaparazziConfig.RenderingMode.FULL_EXPAND -> SessionParams.RenderingMode.FULL_EXPAND
            PaparazziConfig.RenderingMode.SHRINK -> SessionParams.RenderingMode.SHRINK
            null -> {
                // Do not shrink if height or width are set, otherwise it throws exception
                when (previewHeightInDp > 0 || previewWidthInDp > 0) {
                    true -> SessionParams.RenderingMode.FULL_EXPAND
                    false -> SessionParams.RenderingMode.SHRINK
                }
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
            screenWidth = when (previewWidthInDp > 0) {
                true -> previewWidthInPx
                false -> parsedDevice.dimensions.width.toInt()
            },
            density = Density(parsedDevice.densityDpi),
            xdpi = parsedDevice.densityDpi,
            ydpi = parsedDevice.densityDpi,
            size = ScreenSize.valueOf(parsedDevice.screenSize.name),
            ratio = ScreenRatio.valueOf(parsedDevice.screenRatio.name),
            screenRound = ScreenRound.valueOf(parsedDevice.shape.name),
            orientation = ScreenOrientation.valueOf(parsedDevice.orientation.name),
        )
    }
}