package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.Wallpapers

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
    ) { padding ->

        if (showSnackbar) {
            ActionNotSupportedSnackbar(
                snackbarHostState = snackbarHostState,
                onDismiss = { showSnackbar = false }
            )
        }

        CoffeeDrinksScreenUI(
            modifier = Modifier.padding(padding),
            coffeeDrinksState = coffeeDrinksState,
            coffeeShopName = coffeeShopName,
            onCoffeeDrinkItemClick = { showSnackbar = true }
        )
    }
}

@Composable
fun CoffeeDrinksScreenUI(
    modifier: Modifier = Modifier,
    coffeeDrinksState: CoffeeDrinksState,
    coffeeShopName: String,
    onCoffeeDrinkItemClick: () -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        Column {
            CoffeeDrinkAppBar(coffeeShopName)
            CoffeeDrinkList(
                coffeeDrinksState = coffeeDrinksState,
                onCoffeeDrinkItemClick = onCoffeeDrinkItemClick,
            )
        }
    }
}

@Preview(
    name = "with_dynamic_colors",
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "no_dynamic_colors",
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE
)
@Preview(
    apiLevel = 33,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE
)
@Composable
fun PreviewCoffeeDrinkAppBar() {
    AppTheme {
        CoffeeDrinkAppBar()
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
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
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
