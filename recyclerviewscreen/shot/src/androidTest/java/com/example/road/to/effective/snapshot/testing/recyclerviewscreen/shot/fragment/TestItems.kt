package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.fragment

import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
import  com.example.road.to.effective.snapshot.testing.recyclerview.R

enum class HappyPathTestItem(val item: FragmentConfigItem) {
    HAPPY(
        FragmentConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
        ),
    )
}

enum class UnhappyPathTestItem(val item: FragmentConfigItem) {
    UNHAPPY_CUSTOM_THEME_DAY(
        FragmentConfigItem(
            locale = "en",
            theme = R.style.Theme_Custom,
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
        ),
    ),
    UNHAPPY_CUSTOM_THEME_NIGHT(
        FragmentConfigItem(
            locale = "en",
            theme = R.style.Theme_Custom,
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.NORMAL,
        ),
    ),
    UNHAPPY_AR_XB_HUGE(
        FragmentConfigItem(
            locale = "ar_XB",
            uiMode = UiMode.DAY,
            fontSize = FontSize.HUGE,
        ),
    ),
    UNHAPPY_LANDSCAPE_AR_XB(
        FragmentConfigItem(
            locale = "ar_XB",
            uiMode = UiMode.DAY,
            orientation = Orientation.LANDSCAPE,
            fontSize = FontSize.NORMAL,
        ),
    )
}
