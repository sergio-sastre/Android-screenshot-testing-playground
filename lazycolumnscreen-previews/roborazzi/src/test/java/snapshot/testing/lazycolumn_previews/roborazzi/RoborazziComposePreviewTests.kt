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
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RobolectricPreviewInfosApplier
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziConfig
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziOptionsMapper
import snapshot.testing.lazycolumn_previews.roborazzi.utils.filePath

/**
 * Record: ./gradlew :lazycolumnscreen-previews:roborazzi:recordRoborazziDebug
 * Verify: ./gradlew :lazycolumnscreen-previews:roborazzi:verifyRoborazziDebug
 *
 * AndroidComposablePreviewScanner reads only the previews in the "main" source
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
                .filterPreviews { previewParams -> previewParams.apiLevel < 28 }
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
                .scanPackageTrees("snapshot.testing.lazycolumn_previews.roborazzi")
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
    fun snapshot() {
        RobolectricPreviewInfosApplier.applyFor(preview)

        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        ) {
            preview()
        }
    }
}