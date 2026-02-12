package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowSuccessCoffeeDrinksScreen(
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
) {
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSnackbar = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeDrinkAppBar(
    coffeeShopName: String = "",
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.coffee_drinks_title).addLocation(coffeeShopName),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
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
