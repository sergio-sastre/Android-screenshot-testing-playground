package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.fragment

import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import  com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem

enum class HappyPathTestItem(val item: ViewConfigItem) {
    PORTRAIT(
        ViewConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: ViewConfigItem) {
    CUSTOM_THEME_DAY(
        ViewConfigItem(
            locale = "en",
            theme = R.style.Theme_Custom,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
        ),
    ),
    CUSTOM_THEME_NIGHT(
        ViewConfigItem(
            locale = "en",
            theme = R.style.Theme_Custom,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.NORMAL,
        ),
    ),
    AR_XB_HUGE(
        ViewConfigItem(
            locale = "ar_XB",
            uiMode = UiMode.DAY,
            fontSize = FontSize.LARGEST,
        ),
    ),
    LANDSCAPE_NIGHT(
        ViewConfigItem(
            locale = "en",
            uiMode = UiMode.NIGHT,
            orientation = Orientation.LANDSCAPE,
            fontSize = FontSize.NORMAL,
        ),
    )
}
