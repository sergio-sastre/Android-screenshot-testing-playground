package com.example.road.to.effective.snapshot.testing.dialogs.dropshots

import android.content.Context
import androidx.annotation.StringRes
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

data class DeleteDialogTestItem(
    val viewConfig: ViewConfigItem = ViewConfigItem(),
    val bulletTexts: List<Int>,
)

private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): List<Int> =
    emptyList<Int>().toMutableList().apply {
        repeat(timesRepeated) { add(resource) }
    }

fun itemArray(context: Context, @StringRes resources: List<Int>): Array<String> =
    resources.map { context.getString(it) }.toTypedArray()

enum class HappyPathTestItem(val deleteItem: DeleteDialogTestItem) {
    NORMAL(
        DeleteDialogTestItem(bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest))
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
        ),
    ),
    SUFFOCATED_NIGHT(
        DeleteDialogTestItem(
            viewConfig = ViewConfigItem(
                fontSize = FontSize.HUGE,
                uiMode = UiMode.NIGHT,
            ),
            bulletTexts = repeatedItem(7, R.string.largest),
        ),
    ),
}
