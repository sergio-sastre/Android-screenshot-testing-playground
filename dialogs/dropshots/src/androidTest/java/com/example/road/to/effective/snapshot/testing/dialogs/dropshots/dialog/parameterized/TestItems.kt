package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized

import android.content.Context
import androidx.annotation.StringRes
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode
import com.example.road.to.effective.snapshot.testing.dialogs.R

enum class DialogWidth(val widthInPx: Int?) {
    NARROW(800),
    NORMAL(1_200),
    WIDE(1_600),
}

data class DeleteDialogTestItem(
    val viewConfig: ViewConfigItem = ViewConfigItem(),
    val bulletTexts: List<Int>,
    val dialogWidth: DialogWidth,
)

private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): List<Int> =
    emptyList<Int>().toMutableList().apply {
        repeat(timesRepeated) { add(resource) }
    }

fun itemArray(context: Context, @StringRes resources: List<Int>): Array<String> =
    resources.map { context.getString(it) }.toTypedArray()

enum class HappyPathTestItem(val deleteItem: DeleteDialogTestItem) {
    NORMAL(
        DeleteDialogTestItem(
            bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
            dialogWidth = DialogWidth.NORMAL,
        )
    ),
}

enum class UnhappyPathTestItem(val deleteItem: DeleteDialogTestItem) {
    SPACIOUS_NIGHT(
        DeleteDialogTestItem(
            viewConfig = ViewConfigItem(
                fontSize = FontSize.SMALL,
                uiMode = UiMode.NIGHT,
            ),
            bulletTexts = listOf(R.string.shortest),
            dialogWidth = DialogWidth.WIDE,
        ),
    ),
    SUFFOCATED_NIGHT(
        DeleteDialogTestItem(
            viewConfig = ViewConfigItem(
                fontSize = FontSize.LARGEST,
                uiMode = UiMode.NIGHT,
            ),
            bulletTexts = repeatedItem(7, R.string.largest),
            dialogWidth = DialogWidth.NARROW,
        ),
    ),
}
