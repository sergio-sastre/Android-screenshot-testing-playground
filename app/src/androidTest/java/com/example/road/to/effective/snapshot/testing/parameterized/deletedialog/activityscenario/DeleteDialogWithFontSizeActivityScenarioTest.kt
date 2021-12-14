package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.activityscenario

import androidx.annotation.StringRes
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.config.DialogTheme.MATERIAL_DARK_DIALOG
import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper.snapDeleteDialogWithActivityScenario
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale.*

@RunWith(Parameterized::class)
class DeleteDialogWithFontSizeActivityScenarioTest(private val testItem: DeleteDialogTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<DeleteDialogTestItem> =
            arrayOf(
                // spacious item: small font, little wording, wide view
                DeleteDialogTestItem(
                    SMALL,
                    MATERIAL_DARK_DIALOG,
                    arrayOf(R.string.shortest),
                    DialogWidth.WIDE,
                    "DARK_SMALL_WIDE"
                ),
                // suffocated item: huge font, a lot of wording, narrow view
                DeleteDialogTestItem(
                    HUGE,
                    MATERIAL_DARK_DIALOG,
                    repeatedItem(7, R.string.largest),
                    DialogWidth.NARROW,
                    "DARK_HUGE_NARROW"
                )
            )
    }

    @UnhappyPath
    @Test
    fun snapDialog() {
        snapDeleteDialogWithActivityScenario(testItem)
    }
}

private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): Array<Int> =
    emptyList<Int>().toMutableList().apply {
        repeat(timesRepeated) { add(resource) }
    }.toTypedArray()