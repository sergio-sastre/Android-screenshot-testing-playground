package snapshot.testing.lazycolumn_previews.paparazzi.utils

import android.content.res.Configuration
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_4A
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation

object PaparazziPreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): Paparazzi {
        val previewPaparazziConfig = preview.getAnnotation<PaparazziConfig>()
        return Paparazzi(
            deviceConfig = PIXEL_4A.copy(
                nightMode = when (preview.previewInfo.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                    true -> NightMode.NIGHT
                    false -> NightMode.NOTNIGHT
                },
                fontScale = preview.previewInfo.fontScale,
                locale = preview.previewInfo.locale.ifBlank { "en" }
            ),
            supportsRtl = true,
            renderingMode = when(previewPaparazziConfig?.renderingMode){
                PaparazziConfig.RenderingMode.NORMAL -> SessionParams.RenderingMode.NORMAL
                PaparazziConfig.RenderingMode.V_SCROLL -> SessionParams.RenderingMode.V_SCROLL
                PaparazziConfig.RenderingMode.H_SCROLL -> SessionParams.RenderingMode.H_SCROLL
                PaparazziConfig.RenderingMode.FULL_EXPAND -> SessionParams.RenderingMode.FULL_EXPAND
                PaparazziConfig.RenderingMode.SHRINK -> SessionParams.RenderingMode.SHRINK
                null -> SessionParams.RenderingMode.SHRINK
            },
            renderExtensions = when (previewPaparazziConfig?.enableAccessibility == true) {
                true -> setOf(AccessibilityRenderExtension())
                false -> emptySet()
            }
        )
    }
}