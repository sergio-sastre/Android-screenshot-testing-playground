package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.parameterized

import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.PhoneOrientation

data class DeviceConfig(
    val nightMode: NightMode = NightMode.NOTNIGHT,
    val fontScale: Float = 1.0f,
    val locale: String = "en",
    val phoneOrientation: PhoneOrientation,
)

enum class HappyPathTestItem(val item: DeviceConfig) {
    PORTRAIT(
        DeviceConfig(
            locale = "en",
            phoneOrientation = PhoneOrientation.PORTRAIT,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.0f,
        ),
    )
}

enum class UnhappyPathTestItem(val item: DeviceConfig) {
    LANDSCAPE(
        DeviceConfig(
            locale = "en",
            phoneOrientation = PhoneOrientation.LANDSCAPE,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.0f,
        ),
    ),
    HUGE(
        DeviceConfig(
            locale = "en",
            phoneOrientation = PhoneOrientation.PORTRAIT,
            nightMode = NightMode.NOTNIGHT,
            fontScale = 1.3f,
        ),
    ),
    SMALL(
        DeviceConfig(
            locale = "en",
            phoneOrientation = PhoneOrientation.PORTRAIT,
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
