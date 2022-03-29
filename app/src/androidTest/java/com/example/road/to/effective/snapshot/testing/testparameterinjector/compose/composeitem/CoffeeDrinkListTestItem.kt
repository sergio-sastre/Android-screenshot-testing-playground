package com.example.road.to.effective.snapshot.testing.testparameterinjector.compose.composeitem

import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem

data class CoffeeDrinkListTestItem(
    val coffeeDrink: CoffeeDrinkItem,
    val configTestItem: ConfigTestItem
)
