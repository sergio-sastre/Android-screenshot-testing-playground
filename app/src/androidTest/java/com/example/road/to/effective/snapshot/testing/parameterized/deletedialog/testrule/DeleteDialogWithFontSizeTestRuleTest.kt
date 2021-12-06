package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.testrule

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.config.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.testrule.FontScaleRules

/**
 * This tests showcase how to use FontScaleTestRule
 */
@RunWith(Parameterized::class)
class DeleteDialogWithFontSizeTestRuleTest(private val testItem: DeleteDialogTestItem) : ScreenshotTest {

    @get:Rule
    val fontSize = FontScaleRules.fontScaleTestRule(testItem.fontScale)

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<DeleteDialogTestItem> = arrayOf(
            DeleteDialogTestItem(
                FontScale.NORMAL,
                DialogTheme.MATERIAL_LIGHT_DIALOG,
                arrayOf(R.string.largest, R.string.middle, R.string.shortest),
                DialogWidth.NORMAL,
                "HAPPY_PATH"
            )
        )
    }

    @HappyPath
    @Test
    fun snapDialog() {
        DeleteDialogSnapshotHelper.snapDeleteDialogWithTestRule(testItem)
    }
}