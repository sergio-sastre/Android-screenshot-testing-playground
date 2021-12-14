package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.testrule

import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.normalItemHappyPath
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper.snapDeleteDialogWithTestRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
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
            normalItemHappyPath()
        )
    }

    @HappyPath
    @Test
    fun snapDialog() {
        snapDeleteDialogWithTestRule(testItem)
    }
}