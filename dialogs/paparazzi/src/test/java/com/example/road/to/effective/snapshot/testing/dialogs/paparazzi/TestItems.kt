package com.example.road.to.effective.snapshot.testing.dialogs.paparazzi

import android.content.Context
import androidx.annotation.StringRes
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.android.resources.NightMode

enum class ScreenWidth(val widthInPx: Int) {
    NARROW(800),
    NORMAL(1_200),
    WIDE(1_600),
}

data class DeviceConfig(
    val nightMode: NightMode = NightMode.NOTNIGHT,
    val fontScale: Float = 1.0f,
    val screenWidth: ScreenWidth = ScreenWidth.NORMAL,
)

data class DeleteDialogTestItem(
    val deviceConfig: DeviceConfig = DeviceConfig(),
    val bulletTexts: List<Int>,
    val screenWidth: ScreenWidth,
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
            screenWidth = ScreenWidth.NORMAL,
        )
    ),
}

enum class UnhappyPathTestItem(val deleteItem: DeleteDialogTestItem) {
    SPACIOUS_NIGHT(
        DeleteDialogTestItem(
            deviceConfig = DeviceConfig(
                nightMode = NightMode.NIGHT,
                fontScale = 0.85f,
            ),
            bulletTexts = listOf(R.string.shortest),
            screenWidth = ScreenWidth.WIDE,
        ),
    ),
    SUFFOCATED_NIGHT(
        DeleteDialogTestItem(
            deviceConfig = DeviceConfig(
                nightMode = NightMode.NIGHT,
                fontScale = 1.13f,
            ),
            bulletTexts = repeatedItem(7, R.string.largest),
            screenWidth = ScreenWidth.NARROW,
        ),
    ),
}
