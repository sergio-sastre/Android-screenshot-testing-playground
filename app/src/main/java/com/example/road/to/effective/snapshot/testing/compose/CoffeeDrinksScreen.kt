package com.example.road.to.effective.snapshot.testing.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.road.to.effective.snapshot.testing.R

@Composable
fun ShowSuccessCoffeeDrinksScreen(
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
) {
    CoffeeDrinksScreenUI(
        coffeeDrinksState = coffeeDrinksState,
        coffeeShopName = coffeeShopName,
    )
}

@Composable
fun CoffeeDrinksScreenUI(
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
) {
    Surface {
        Column {
            CoffeeDrinkAppBar(coffeeShopName)
            CoffeeDrinkList(
                coffeeDrinksState = coffeeDrinksState
            )
        }
    }
}

@Composable
fun CoffeeDrinkAppBar(
    coffeeShopName: String = "",
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.coffee_drinks_title).addLocation(coffeeShopName),
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

fun String.addLocation(location: String): String =
    if (location.isNotBlank()) {
        this + (" at $location").trimEnd()
    } else {
        this
    }


@Composable
fun CoffeeDrinkList(
    coffeeDrinksState: CoffeeDrinksState
) {
    LazyColumn {
        items(items = coffeeDrinksState.coffeeDrinks) { coffeeDrink ->
            Box {
                CoffeeDrinkList(
                    coffeeDrink = coffeeDrink,
                )
            }
        }
    }
}