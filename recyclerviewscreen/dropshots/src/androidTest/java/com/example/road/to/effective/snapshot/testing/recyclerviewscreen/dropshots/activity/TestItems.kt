package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.activity

import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

enum class HappyPathTestItem(val item: ActivityConfigItem) {
    HAPPY(
        ActivityConfigItem(
            systemLocale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: ActivityConfigItem) {
    UNHAPPY_LANDSCAPE(
        ActivityConfigItem(
            systemLocale = "en",
            orientation = Orientation.LANDSCAPE,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
    UNHAPPY_EN_XA_NIGHT_SMALL_SMALL(
        ActivityConfigItem(
            systemLocale = "en_XA",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.SMALL,
            displaySize = DisplaySize.SMALL,
        ),
    ),
    UNHAPPY_AR_XB_NIGHT_HUGE_LARGEST(
        ActivityConfigItem(
            systemLocale = "ar_XB",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.HUGE,
            displaySize = DisplaySize.LARGEST,
        ),
    )
}
