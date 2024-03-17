package com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary

import android.content.Context
import androidx.annotation.StringRes
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.FontSizeScale
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView

data class DeleteDialogTestItem(
    val viewConfig: ScreenshotConfigForView = ScreenshotConfigForView(),
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
        DeleteDialogTestItem(
            viewConfig = ScreenshotConfigForView(theme = "Theme.RoadToEffectiveSnapshotTesting"),
            bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
        )
    ),
}

enum class UnhappyPathTestItem(val deleteItem: DeleteDialogTestItem) {
    SMALL_NIGHT(
        DeleteDialogTestItem(
            viewConfig = ScreenshotConfigForView(
                fontSize = FontSize.SMALL,
                uiMode = UiMode.NIGHT,
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            bulletTexts = listOf(R.string.shortest),
        ),
    ),
    HUGE_NIGHT(
        DeleteDialogTestItem(
            viewConfig = ScreenshotConfigForView(
                fontSize = FontSizeScale.Value(2f),
                uiMode = UiMode.NIGHT,
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            bulletTexts = repeatedItem(7, R.string.largest),
        ),
    ),
}
