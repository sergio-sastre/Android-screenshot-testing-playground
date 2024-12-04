package snapshot.testing.lazycolumn_previews.roborazzi

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ActivityScenario
import com.github.takahirom.roborazzi.DefaultFileNameGenerator
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.InternalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziTransparentActivity
import com.github.takahirom.roborazzi.applyToRobolectricConfiguration
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.provideRoborazziContext
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
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RobolectricPreviewInfosApplier
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziConfig
import snapshot.testing.lazycolumn_previews.roborazzi.utils.RoborazziOptionsMapper
import snapshot.testing.lazycolumn_previews.roborazzi.utils.setBackgroundColor
import snapshot.testing.lazycolumn_previews.roborazzi.utils.filePath
import snapshot.testing.lazycolumn_previews.roborazzi.utils.resize
import snapshot.testing.lazycolumn_previews.roborazzi.utils.setShadowDisplay

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

object ComposablePreviewProviderApi29 : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>?> =
        cachedPreviews.filter { it.previewInfo.apiLevel == 29 }.ifEmpty { listOf(null) }
}

@RunWith(RobolectricTestParameterInjector::class)
class PaparazziComposePreviewTests {
    //@get:Rule
    //val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
    @get:Rule
    val emptyComposeRule = RobolectricActivityScenarioForComposableRule()
     **/

    @OptIn(ExperimentalRoborazziApi::class)
    private fun snapshot(preview: ComposablePreview<AndroidPreviewInfo>) {

        /*
        when (preview.previewInfo.widthDp != -1 || preview.previewInfo.heightDp != -1) {
            // Requires an Activity with its display dimensions adjusted
            true -> {
                preview.captureRoboImage2(
                    filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
                    roborazziOptions = RoborazziOptionsMapper.createFor(preview),
                )
            }

            false -> {
                preview.captureRoboImage(
                    filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
                    roborazziOptions = RoborazziOptionsMapper.createFor(preview)
                )
            }
        }

         */

        preview.captureRoboImage3(
            filePath = filePath(AndroidPreviewScreenshotIdBuilder(preview).build()),
            roborazziOptions = RoborazziOptionsMapper.createFor(preview)
        )
    }

    /*
    @GraphicsMode(NATIVE)
    @Config(sdk = [29])
    @Test
    fun snapshotApi29(
        @TestParameter(valuesProvider = ComposablePreviewProviderApi29::class)
        preview: ComposablePreview<AndroidPreviewInfo>?,
    ) {
        Assume.assumeTrue("Skipping api 29", preview != null)
        snapshot(preview!!)
    }

     */

    @GraphicsMode(NATIVE)
    @Config(sdk = [35])
    @Test
    fun snapshotApi35(
        @TestParameter(valuesProvider = ComposablePreviewProviderApi35::class)
        preview: ComposablePreview<AndroidPreviewInfo>,
    ) {
        snapshot(preview)
    }

    /*
    @GraphicsMode(NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapshotApi31(
        @TestParameter(valuesProvider = ComposablePreviewProviderApi31::class)
        preview: ComposablePreview<AndroidPreviewInfo>,
    ) {
        snapshot(preview)
    }

     */
}

@OptIn(ExperimentalRoborazziApi::class, InternalRoborazziApi::class)
fun ComposablePreview<AndroidPreviewInfo>.captureRoboImage2(
    filePath: String = DefaultFileNameGenerator.generateFilePath("png"),
    roborazziOptions: RoborazziOptions = provideRoborazziContext().options,
) {
    if (!roborazziOptions.taskType.isEnabled()) return

    applyToRobolectricConfiguration()

    val widthDp = previewInfo.widthDp
    val heightDp = previewInfo.heightDp

    // Requires activity to adjust Display to width and height
    val activityScenario = ActivityScenario.launch(ComponentActivity::class.java)

    activityScenario.onActivity { activity ->
        activity.setShadowDisplay(
            widthDp = widthDp,
            heightDp = heightDp
        )

        activity.setBackgroundColor(
            showBackground = previewInfo.showBackground,
            backgroundColor = previewInfo.backgroundColor
        )
    }

    activityScenario.use {
        activityScenario.onActivity { activity ->

            activity.setContent(content = resize(widthDp, heightDp))

            val composeView = activity.window.decorView
                .findViewById<ViewGroup>(android.R.id.content)
                .getChildAt(0) as ComposeView
            val viewRootForTest = composeView.getChildAt(0) as ViewRootForTest

            viewRootForTest.view.captureRoboImage(filePath, roborazziOptions)
        }

        // Closing the activity is necessary to prevent memory leaks.
        // If multiple captureRoboImage calls occur in a single test,
        // they can lead to an activity leak.
    }
}

@OptIn(ExperimentalRoborazziApi::class, InternalRoborazziApi::class)
fun ComposablePreview<AndroidPreviewInfo>.captureRoboImage3(
    filePath: String = DefaultFileNameGenerator.generateFilePath("png"),
    roborazziOptions: RoborazziOptions = provideRoborazziContext().options
) {
    if (!roborazziOptions.taskType.isEnabled()) return
    //registerRoborazziActivityToRobolectricIfNeeded()
    RobolectricPreviewInfosApplier.applyFor(this) //applyToRobolectricConfiguration()

    val activityScenario = ActivityScenario.launch(ComponentActivity::class.java)

    activityScenario.onActivity { activity ->
        activity.setShadowDisplay(
            widthDp = previewInfo.widthDp,
            heightDp = previewInfo.heightDp
        )
    }

    activityScenario.use {
        activityScenario.onActivity { activity ->
            
            activity.setBackgroundColor(
                showBackground = previewInfo.showBackground,
                backgroundColor = previewInfo.backgroundColor
            )

            activity.setContent(
                content = resize(widthDp = previewInfo.widthDp, heightDp = previewInfo.heightDp)
            )
            val composeView = activity.window.decorView
                .findViewById<ViewGroup>(android.R.id.content)
                .getChildAt(0) as ComposeView
            val viewRootForTest = composeView.getChildAt(0) as ViewRootForTest
            viewRootForTest.view.captureRoboImage(filePath, roborazziOptions)
        }

        // Closing the activity is necessary to prevent memory leaks.
        // If multiple captureRoboImage calls occur in a single test,
        // they can lead to an activity leak.
    }
}

/**
 * WARNING:
 * For this to work, it requires that the Display is within the widthDp and heightDp dimensions
 */
fun ComposablePreview<AndroidPreviewInfo>.size(
    widthDp: Int,
    heightDp: Int,
): @Composable () -> Unit {
    val resizedPreview = @Composable {
        val modifier = when {
            widthDp > 0 && heightDp > 0 -> Modifier.size(widthDp.dp, heightDp.dp)
            widthDp > 0 -> Modifier.width(widthDp.dp)
            heightDp > 0 -> Modifier.height(heightDp.dp)
            else -> Modifier
        }
        Box(modifier = modifier) { this@size.invoke() }
    }
    return resizedPreview
}