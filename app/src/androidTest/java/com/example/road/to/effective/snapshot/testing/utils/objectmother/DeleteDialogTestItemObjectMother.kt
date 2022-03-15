package com.example.road.to.effective.snapshot.testing.utils.objectmother

import androidx.annotation.StringRes
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.testparameterinjector.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

object DeleteDialogTestItemObjectMother {

    fun spaciousDialogUnhappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontSize.SMALL,
            UiMode.NIGHT,
            arrayOf(R.string.shortest),
            DialogWidth.WIDE,
            "DARK_SMALL_WIDE"
        )

    fun suffocatedItemUnhappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontSize.HUGE,
            UiMode.NIGHT,
            repeatedItem(7, R.string.largest),
            DialogWidth.NARROW,
            "DARK_HUGE_NARROW"
        )

    fun normalItemHappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontSize.NORMAL,
            UiMode.DAY,
            arrayOf(R.string.largest, R.string.middle, R.string.shortest),
            DialogWidth.NORMAL,
            "HAPPY_PATH"
        )

    private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): Array<Int> =
        emptyList<Int>().toMutableList().apply {
            repeat(timesRepeated) { add(resource) }
        }.toTypedArray()

}