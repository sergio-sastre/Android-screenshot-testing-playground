package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.bitmap

import android.graphics.Color.TRANSPARENT
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
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
 * Moreover, PixelCopy requires API 26+, defaulting to Canvas (no elevation) in lower APIs.
 *
 * - Canvas: draws UI components to bitmap without considering elevation. Unlike PixelCopy, it fully
 * screenshots the UI component under tests without resizing it even though it goes beyond the device
 * screen
 */
class MemoriseViewHolderToBitmapTest: ScreenshotTest {

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = ViewConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            ),
            backgroundColor = TRANSPARENT,
        )

    private fun inflateViewHolder(): RecyclerView.ViewHolder {
        val activity = activityScenarioForViewRule.activity
        val layout = activityScenarioForViewRule.inflateAndWaitForIdle(R.layout.memorise_row)

        return waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = false, activity = activity))
            }
        }
    }

    // For API < 26, drawToBitmapWithElevation defaults to Canvas. Thus, draws no elevation
    @BitmapTest
    @Test
    fun snapViewHolderWithPixelCopy() {
        compareScreenshot(
            bitmap = inflateViewHolder().drawToBitmapWithElevation(),
            name = "MemoriseView_BitmapWithElevation"
        )
    }

    @BitmapTest
    @Test
    fun snapViewHolderWithCanvas() {
        compareScreenshot(
            bitmap = inflateViewHolder().itemView.drawToBitmap(),
            name = "MemoriseView_BitmapWithoutElevation"
        )
    }
}
