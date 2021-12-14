package com.example.road.to.effective.snapshot.testing.utils.objectmother

import androidx.annotation.StringRes
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.config.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import sergio.sastre.fontsize.FontScale

object DeleteDialogTestItemObjectMother {

    fun spaciousDialogUnhappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontScale.SMALL,
            DialogTheme.MATERIAL_DARK_DIALOG,
            arrayOf(R.string.shortest),
            DialogWidth.WIDE,
            "DARK_SMALL_WIDE"
        )

    fun suffocatedItemUnhappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontScale.HUGE,
            DialogTheme.MATERIAL_DARK_DIALOG,
            repeatedItem(7, R.string.largest),
            DialogWidth.NARROW,
            "DARK_HUGE_NARROW"
        )

    fun normalItemHappyPath(): DeleteDialogTestItem =
        DeleteDialogTestItem(
            FontScale.NORMAL,
            DialogTheme.MATERIAL_LIGHT_DIALOG,
            arrayOf(R.string.largest, R.string.middle, R.string.shortest),
            DialogWidth.NORMAL,
            "HAPPY_PATH"
        )

    private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): Array<Int> =
        emptyList<Int>().toMutableList().apply {
            repeat(timesRepeated) { add(resource) }
        }.toTypedArray()

}