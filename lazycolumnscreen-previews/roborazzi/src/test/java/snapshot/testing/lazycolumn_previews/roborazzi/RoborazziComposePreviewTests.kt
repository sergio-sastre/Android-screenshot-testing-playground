package snapshot.testing.lazycolumn_previews.roborazzi

import androidx.compose.ui.tooling.preview.Devices
import com.github.takahirom.roborazzi.Dump
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziComposeOptions
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.background
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.fontScale
import com.github.takahirom.roborazzi.locale
import com.github.takahirom.roborazzi.previewDevice
import com.github.takahirom.roborazzi.size
import com.github.takahirom.roborazzi.uiMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterValuesProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestParameterInjector
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziComposeOptionsMapper
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
 * It currently supports Locale, UiMode, FontScale, Device, HeightDp, WidthDp, ShowBackground and BackgroundColor out of the box.
 * Check it here:
 * https://github.com/takahirom/roborazzi#generate-compose-preview-screenshot-tests
 *
 */

private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
    AndroidComposablePreviewScanner()
        .scanPackageTrees("snapshot.testing.lazycolumn_previews.roborazzi")
        .includeAnnotationInfoForAllOf(RoborazziConfig::class.java)
        .getPreviews()
}

object ComposablePreviewProviderApi35 : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        // apiLevel default = -1 -> renders with 35
        cachedPreviews.filter { it.previewInfo.apiLevel < 28 || it.previewInfo.apiLevel == 35 }
}

object ComposablePreviewProviderApi31 : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        cachedPreviews.filter { it.previewInfo.apiLevel == 31 }
}

@RunWith(RobolectricTestParameterInjector::class)
class RoborazziComposePreviewTests {

    @OptIn(ExperimentalRoborazziApi::class)
    private fun snapshot(preview: ComposablePreview<AndroidPreviewInfo>) {
        captureRoboImage(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview),
            roborazziComposeOptions = RoborazziComposeOptionsMapper.createFor(preview)
        ){
            preview()
        }
    }
    
    @GraphicsMode(NATIVE)
    @Config(sdk = [35])
    @Test
    fun snapshotApi35(
        @TestParameter(valuesProvider = ComposablePreviewProviderApi35::class)
        preview: ComposablePreview<AndroidPreviewInfo>,
    ) {
        snapshot(preview)
    }

    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapshotApi31(
        @TestParameter(valuesProvider = ComposablePreviewProviderApi31::class)
        preview: ComposablePreview<AndroidPreviewInfo>,
    ) {
        snapshot(preview)
    }
}