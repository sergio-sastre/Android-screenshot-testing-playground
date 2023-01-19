package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.bitmap

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewFragment
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Example of Tests for Bitmaps to take more realistic screenshots.
 * For that, we draw the Views under tests to bitmaps using PixelCopy & Canvas,each of them
 * obtaining different results:
 *
 * - PixelCopy: draws UI components to bitmap considering elevation. However, use carefully if
 * screenshooting Dialogs/Views/Composables whose size goes beyond the device screen (e.g. ScrollViews).
 * PixelCopy resizes the UI component under test to fit it inside the window. Better use Canvas instead.
 * Moreover, PixelCopy requires API 26+.
 * drawToBitmapWithElevation() uses PixelCopy but defaults to Canvas (i.e. no elevation) in lower APIs.
 *
 * - Canvas: draws UI components to bitmap without considering elevation. Unlike PixelCopy, it fully
 * screenshots the UI component under tests without resizing it even though it goes beyond the device
 * screen
 */
class RecyclerViewFragmentToBitmapTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<RecyclerViewFragment>(
            config = FragmentConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            ),
        )

    @BitmapTest
    @Test
    fun snapRecyclerViewActivityWithPixelCopy(){
        dropshots.assertSnapshot(
            bitmap = fragmentScenarioConfiguratorRule.fragment.drawToBitmapWithElevation(),
            name = "RecyclerViewFragment_BitmapWithElevation"
        )
    }

    @BitmapTest
    @Test
    fun snapRecyclerViewActivityWithCanvas(){
        dropshots.assertSnapshot(
            bitmap = fragmentScenarioConfiguratorRule.fragment.drawToBitmap(),
            name = "RecyclerViewFragment_BitmapWithoutElevation"
        )
    }
}
