package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.bitmap

import android.app.Activity
import android.graphics.Color.TRANSPARENT
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder.MemoriseTestItemGenerator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.processor.capture.canvasCapture
import dev.testify.core.processor.capture.pixelCopyCapture
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForView
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.android_testify.setViewHolderForScreenshot
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Tests for Bitmaps to take more realistic screenshots.
 * For that, we draw the Views under tests to bitmaps using PixelCopy & Canvas,each of them
 * obtaining different results:
 *
 * - PixelCopy: draws UI components to bitmap considering elevation. However, use carefully if
 * screenshooting Dialogs/Views/Composables whose size goes beyond the device screen (e.g. ScrollViews).
 * PixelCopy resizes the UI component under test to fit it inside the window. Better use Canvas instead.
 * Moreover, PixelCopy requires API 26+.
 *
 * - Canvas: draws UI components to bitmap without considering elevation. Unlike PixelCopy, it fully
 * screenshots the UI component under tests without resizing it even though it goes beyond the device
 * screen
 */
class MemoriseViewHolderToBitmapTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        activityBackgroundColor = TRANSPARENT,
        exactness = 0.85f,
        config = ViewConfigItem(
            uiMode = UiMode.DAY,
            locale = "en",
            orientation = Orientation.PORTRAIT,
        ),
    )

    private fun createMemoriseViewHolder(
        activity: Activity,
        container: ViewGroup
    ): RecyclerView.ViewHolder =
        MemoriseViewHolder(
            container = container,
            itemEventListener = null,
            animationDelay = 0L
        ).apply {
            bind(
                MemoriseTestItemGenerator.generateMemoriseItem(
                    rightAligned = false,
                    activity = activity
                )
            )
        }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapViewHolderWithCanvas() {
        screenshotRule
            .setViewHolderForScreenshot(R.layout.memorise_row) { targetLayout ->
                createMemoriseViewHolder(screenshotRule.activity, targetLayout)
            }
            .configure { this@configure.captureMethod = ::canvasCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "MemoriseViewHolder_BitmapWithoutElevation")
    }

    @ScreenshotInstrumentation
    @BitmapTest
    @Test
    fun snapViewHolderWithPixelCopy() {
        screenshotRule
            .setViewHolderForScreenshot(R.layout.memorise_row) { targetLayout ->
                createMemoriseViewHolder(screenshotRule.activity, targetLayout)
            }
            .configure { this@configure.captureMethod = ::pixelCopyCapture }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "MemoriseViewHolder_BitmapWithElevation")
    }
}
