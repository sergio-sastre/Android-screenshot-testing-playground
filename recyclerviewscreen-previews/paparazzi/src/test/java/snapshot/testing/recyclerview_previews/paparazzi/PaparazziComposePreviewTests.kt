package snapshot.testing.recyclerview_previews.paparazzi

import app.cash.paparazzi.InstantAnimationsRule
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.google.testing.junit.testparameterinjector.TestParameterValuesProvider
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import snapshot.testing.recyclerview_previews.paparazzi.utils.Background
import snapshot.testing.recyclerview_previews.paparazzi.utils.PaparazziConfig
import snapshot.testing.recyclerview_previews.paparazzi.utils.PaparazziPreviewRule

/**
 * Record: ./gradlew :recyclerviewscreen-previews:paparazzi:recordPaparazziDebug
 * Verify: ./gradlew :recyclerviewscreen-previews:paparazzi:verifyPaparazziDebug
 *
 * AndroidComposablePreviewScanner reads only the previews in the "main" source
 */
object ComposablePreviewProvider : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner()
            .scanPackageTrees(
                "snapshot.testing.recyclerview_previews.paparazzi"
            )
            .includeAnnotationInfoForAllOf(PaparazziConfig::class.java)
            .getPreviews()
}

@RunWith(TestParameterInjector::class)
class PaparazziComposePreviewTests(
    @TestParameter(valuesProvider = ComposablePreviewProvider::class)
    val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    @get:Rule
    val paparazzi = PaparazziPreviewRule.createFor(preview)

    @get:Rule
    val instantAnimation = InstantAnimationsRule()

    @Test
    fun previewTests() {
        paparazzi.snapshot(
            name = AndroidPreviewScreenshotIdBuilder(preview)
                // Paparazzi screenshot names already include className and methodName
                // so ignore them to avoid them duplicated what might throw a FileNotFoundException
                // due to the longName
                .ignoreClassName()
                .ignoreMethodName()
                .build()
        ) {
            Background(
                showBackground = preview.previewInfo.showBackground,
                backgroundColor = preview.previewInfo.backgroundColor,
            ) {
                preview()
            }
        }
    }
}