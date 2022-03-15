package com.example.road.to.effective.snapshot.testing.utils

import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

data class ConfigTestItem(
    val orientation: Orientation = Orientation.PORTRAIT,
    val uiMode: UiMode = UiMode.DAY,
    val locale: String = "en",
    val fontSize: FontSize = FontSize.NORMAL,
    val name: String
)
