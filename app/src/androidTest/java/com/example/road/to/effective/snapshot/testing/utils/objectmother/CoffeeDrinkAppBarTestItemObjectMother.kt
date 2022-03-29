package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

object CoffeeDrinkAppBarTestItemObjectMother {

    fun unhappyPath(): ConfigTestItem =
        ConfigTestItem(
            fontSize = FontSize.HUGE,
            uiMode = UiMode.NIGHT,
            orientation = Orientation.LANDSCAPE,
            locale = "ar_XB",
            name = "CoffeeDrinkAppBar_Unhappy"
        )

    fun happyPath(): ConfigTestItem =
        ConfigTestItem(
            fontSize = FontSize.NORMAL,
            uiMode = UiMode.DAY,
            orientation = Orientation.PORTRAIT,
            locale = "en",
            name = "CoffeeDrinkAppBar_Happy"
        )
}
