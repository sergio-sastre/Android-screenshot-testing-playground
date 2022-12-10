package com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.paparazzi.parameterized

import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.R
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.CoffeeDrinkItem

data class DeviceConfig(
    val nightMode: NightMode = NightMode.NOTNIGHT,
    val fontScale: Float = 1.0f,
    val locale: String = "en",
    val screenOrientation: ScreenOrientation,
)

enum class HappyPathTestItem(val item: DeviceConfig) {
    HAPPY(
        DeviceConfig(
            locale = "en",
            screenOrientation = ScreenOrientation.PORTRAIT,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.0f,
        ),
    )
}

enum class UnhappyPathTestItem(val item: DeviceConfig) {
    UNHAPPY_LANDSCAPE(
        DeviceConfig(
            locale = "en",
            screenOrientation = ScreenOrientation.LANDSCAPE,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.0f,
        ),
    ),
    UNHAPPY_HUGE(
        DeviceConfig(
            locale = "en",
            screenOrientation = ScreenOrientation.PORTRAIT,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.3f,
        ),
    ),

    UNHAPPY_SMALL(
        DeviceConfig(
            locale = "en",
            screenOrientation = ScreenOrientation.PORTRAIT,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 0.85f,
        ),
    )
}

internal val coffeeDrink =
    CoffeeDrinkItem(
        id = 1L,
        name = "Americano",
        imageUrl = R.drawable.americano_small,
        description =
        """
        Americano is a type of coffee drink prepared by diluting an espresso with hot water,
        giving it a similar strength to, but different flavour from, traditionally brewed coffee.
        """.trimIndent(),
        ingredients = "Espresso, Water",
        isFavourite = false
    )
