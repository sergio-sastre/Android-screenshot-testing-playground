package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.testparameterinjector.compose.composeitem.CoffeeDrinkListTestItem
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

object CoffeeDrinkListTestItemObjectMother {

    private val coffeeDrink = CoffeeDrinkItem(
        id = 1L,
        name = "Americano",
        imageUrl = R.drawable.americano_small,
        description = "Americano is a type of coffee drink prepared by diluting an espresso with hot water, giving it a similar strength to, but different flavour from, traditionally brewed coffee. ",
        ingredients = "Espresso, Water",
        isFavourite = false
    )

    fun happyPath(): CoffeeDrinkListTestItem =
        CoffeeDrinkListTestItem(
            coffeeDrink = coffeeDrink,
            configTestItem = ConfigTestItem(
                fontSize = FontSize.NORMAL,
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                locale = "en",
                name = "CoffeeDrinkAppBar_Happy"
            )
        )

    fun unhappyPath(): CoffeeDrinkListTestItem =
        CoffeeDrinkListTestItem(
            coffeeDrink = coffeeDrink,
            configTestItem = ConfigTestItem(
                fontSize = FontSize.HUGE,
                uiMode = UiMode.NIGHT,
                orientation = Orientation.LANDSCAPE,
                locale = "en",
                name = "CoffeeDrinkAppBar_Unhappy"
            ),
        )
}
