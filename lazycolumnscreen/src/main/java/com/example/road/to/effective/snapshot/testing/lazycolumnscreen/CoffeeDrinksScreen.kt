package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowSuccessCoffeeDrinksScreen(
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
) {
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {

        if (showSnackbar) {
            ActionNotSupportedSnackbar(
                snackbarHostState = snackbarHostState,
                onDismiss = { showSnackbar = false }
            )
        }

        CoffeeDrinksScreenUI(
            coffeeDrinksState = coffeeDrinksState,
            coffeeShopName = coffeeShopName,
            onCoffeeDrinkItemClick = { showSnackbar = true }
        )
    }
}

@Composable
fun CoffeeDrinksScreenUI(
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
    onCoffeeDrinkItemClick: () -> Unit,
) {
    Surface {
        Column {
            CoffeeDrinkAppBar(coffeeShopName)
            CoffeeDrinkList(
                coffeeDrinksState = coffeeDrinksState,
                onCoffeeDrinkItemClick = onCoffeeDrinkItemClick,
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
    coffeeDrinksState: CoffeeDrinksState,
    onCoffeeDrinkItemClick: () -> Unit,
) {
    LazyColumn {
        items(items = coffeeDrinksState.coffeeDrinks) { coffeeDrink ->
            Box {
                CoffeeDrinkList(
                    modifier = Modifier.clickable { onCoffeeDrinkItemClick() },
                    coffeeDrink = coffeeDrink,
                )
            }
        }
    }
}

@Composable
fun ActionNotSupportedSnackbar(
    snackbarHostState: SnackbarHostState,
    onDismiss: () -> Unit,
) {

    LaunchedEffect(key1 = snackbarHostState) {
        val result = snackbarHostState.showSnackbar(
            message = "Action not supported",
            duration = SnackbarDuration.Long,
        )

        when (result) {
            SnackbarResult.Dismissed -> onDismiss()
            SnackbarResult.ActionPerformed -> {}
        }
    }
}
