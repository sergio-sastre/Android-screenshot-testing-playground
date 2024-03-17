package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.activity

import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

enum class HappyPathTestItem(val item: ActivityConfigItem) {
    PORTRAIT(
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
    EN_XA_NIGHT_SMALL_SMALL(
        ActivityConfigItem(
            systemLocale = "en_XA",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.SMALL,
            displaySize = DisplaySize.SMALL,
        ),
    ),
    AR_XB_NIGHT_HUGE_LARGEST(
        ActivityConfigItem(
            systemLocale = "ar_XB",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.LARGEST,
            displaySize = DisplaySize.LARGEST,
        ),
    ),
    LANDSCAPE(
        ActivityConfigItem(
            systemLocale = "en",
            orientation = Orientation.LANDSCAPE,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
}
