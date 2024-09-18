package snapshot.testing.lazycolumn_previews.roborazzi

import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RenderPreview
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RobolectricPreviewInfosApplier
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziConfig
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziOptionsMapper
import snapshot.testing.lazycolumn_previews.roborazzi.utils.filePath

/**
 * Record: ./gradlew :lazycolumnscreen-previews:roborazzi:recordRoborazziDebug
 * Verify: ./gradlew :lazycolumnscreen-previews:roborazzi:verifyRoborazziDebug
 *
 * AndroidComposablePreviewScanner reads only the previews in the "main" source
 *
 * INFO: You can also configure Roborazzi's plugin to automatically generate
 * screenshot tests out of @Previews without these Parameterized tests.
 * However, it currently supports only Locale, UiMode & FontScale out of the box.
 * Check it here:
 * https://github.com/takahirom/roborazzi#generate-compose-preview-screenshot-tests
 *
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class RoborazziApiLevelUnder28ComposePreviewTests(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    companion object {
        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
            AndroidComposablePreviewScanner()
                .scanPackageTrees("snapshot.testing.lazycolumn_previews.roborazzi")
                .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
                // Native graphics do not work fine on API < 28, and default value is -1
                .filterPreviews {
                    preview -> preview.apiLevel < 28 || preview.apiLevel == 34
                }
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [34])
    @Test
    fun snapshot() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            RenderPreview(preview)
        }
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class RoborazziApiLevelUnder30ComposePreviewTests(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    companion object {
        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
            AndroidComposablePreviewScanner()
                .scanPackageTrees("snapshot.testing.lazycolumn_previews.roborazzi")
                .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
                // Native graphics do not work fine on API < 28, and default value is -1
                .filterPreviews { preview -> preview.apiLevel == 30 }
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [30]) // same as filtered previews
    @Test
    fun snapshot() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            RenderPreview(preview)
        }
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class RoborazziApiLevel33ComposePreviewTests(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    companion object {
        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
            AndroidComposablePreviewScanner()
                .scanPackageTrees("snapshot.testing.lazycolumn_previews.roborazzi")
                .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
                .filterPreviews { preview -> preview.apiLevel == 33 }
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [33]) // same as filtered previews
    @Test
    fun snapshot() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            RenderPreview(preview)
        }
    }
}
