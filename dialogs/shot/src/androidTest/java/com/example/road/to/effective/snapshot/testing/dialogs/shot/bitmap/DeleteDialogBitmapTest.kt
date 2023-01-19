package com.example.road.to.effective.snapshot.testing.dialogs.shot.bitmap

import android.app.Dialog
import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.shot.parameterized.itemArray
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
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

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
class DeleteDialogBitmapTest: ScreenshotTest {

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

    private fun inflateDialog(): Dialog {
        val activity = activityScenarioForViewRule.activity

        return waitForMeasuredDialog {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(
                    activity,
                    listOf(R.string.largest, R.string.middle, R.string.shortest),
                )
            )
        }
    }

    @BitmapTest
    @Test
    fun snapDialogWithPixelCopy(){
        compareScreenshot(
            bitmap = inflateDialog().drawToBitmapWithElevation(),
            name = "DeleteDialog_BitmapWithElevation",
        )
    }

    @BitmapTest
    @Test
    fun snapDialogWithCanvas(){
        compareScreenshot(
            bitmap = inflateDialog().drawToBitmap(),
            name = "DeleteDialog_BitmapWithoutElevation",
        )
    }
}
