package snapshot.testing.recyclerview_previews.roborazzi.utils

import android.content.res.Configuration
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import org.robolectric.RuntimeEnvironment.setFontScale
import org.robolectric.RuntimeEnvironment.setQualifiers
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.domain.RobolectricDeviceQualifierBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

object RobolectricPreviewInfosApplier {
    fun applyFor(preview: ComposablePreview<AndroidPreviewInfo>) {
        setDevice(preview.previewInfo.device)
        setUiMode(preview.previewInfo.uiMode)
        setLocale(preview.previewInfo.locale)
        setFontScale(preview.previewInfo.fontScale)
    }

    private fun setDevice(device: String) {
        val parsedDevice = RobolectricDeviceQualifierBuilder.build(device) ?: RobolectricDeviceQualifiers.Pixel4a
        setQualifiers(parsedDevice)
    }

    private fun setUiMode(uiMode: Int){
        val nightMode =
            when(uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                true -> "night"
                false -> "notnight"
            }
        setQualifiers("+$nightMode")
    }

    private fun setLocale(locale: String) {
        val localeWithFallback = locale.ifBlank { "en" }
        setQualifiers("+$localeWithFallback")
    }
}