package snapshot.testing.recyclerview_previews.roborazzi

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
import snapshot.testing.recyclerview_previews.roborazzi.utils.RobolectricPreviewInfosApplier
import snapshot.testing.recyclerview_previews.roborazzi.utils.RoborazziConfig
import snapshot.testing.recyclerview_previews.roborazzi.utils.RoborazziOptionsMapper
import snapshot.testing.recyclerview_previews.roborazzi.utils.filePath

/**
 * Record: ./gradlew :recyclerviewscreen-previews:roborazzi:recordRoborazziDebug
 * Verify: ./gradlew :recyclerviewscreen-previews:roborazzi:verifyRoborazziDebug
 *
 * AndroidComposablePreviewScanner reads only the previews in the "main" source
 *
 * INFO: You can also configure Roborazzi's plugin to automatically generate
 * screenshot tests out of @Previews without these Parameterized tests.
 * It currently supports Locale, UiMode, FontScale, Device, HeightDp, WidthDp, ShowBackground and BackgroundColor out of the box.
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
                .scanPackageTrees("snapshot.testing.recyclerview_previews.roborazzi")
                .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
                // Native graphics do not work fine on API < 28, and default value is -1
                .filterPreviews { previewParams -> previewParams.apiLevel < 28 }
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [35])
    @Test
    fun snapshotApi35() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            preview()
        }
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class RoborazziApiLevel31ComposePreviewTests(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    companion object {
        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
            AndroidComposablePreviewScanner()
                .scanPackageTrees("snapshot.testing.recyclerview_previews.roborazzi")
                .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
                .filterPreviews { previewParams -> previewParams.apiLevel == 31 }
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [31]) // same as filtered previews
    @Test
    fun snapshotApi31() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            preview()
        }
    }
}