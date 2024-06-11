package com.example.road.to.effective.snapshot.testing.lazycolumnscreen_previews

import android.content.res.Configuration
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_4A
import app.cash.paparazzi.Paparazzi
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

object ComposablePreviewProvider : TestParameter.TestParameterValuesProvider {
    override fun provideValues(): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner()
            .scanPackageTrees(
                "com.example.road.to.effective.snapshot.testing.lazycolumnscreen"
            )
            .getPreviews()
}

object PaparazziPreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): Paparazzi =
        Paparazzi(
            deviceConfig = PIXEL_4A.copy(
                nightMode = when (preview.previewInfo.uiMode) {
                    Configuration.UI_MODE_NIGHT_YES -> NightMode.NIGHT
                    else -> NightMode.NOTNIGHT
                }
            )
        )
}

@RunWith(TestParameterInjector::class)
class PaparazziComposePreviewTests(
    @TestParameter(valuesProvider = ComposablePreviewProvider::class)
    val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    @get:Rule
    val paparazzi = PaparazziPreviewRule.createFor(preview)

    @Test
    fun previewTests() {
        paparazzi.snapshot {
            preview()
        }
    }
}