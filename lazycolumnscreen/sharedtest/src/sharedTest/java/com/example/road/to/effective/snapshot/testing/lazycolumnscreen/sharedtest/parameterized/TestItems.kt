package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import sergio.sastre.uitesting.utils.ScreenshotConfig
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

enum class HappyPathTestItem(val item: ScreenshotConfig) {
    PORTRAIT(
        ScreenshotConfig(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.NORMAL,
        ),
    )
}

enum class UnhappyPathTestItem(val item: ScreenshotConfig) {
    LANDSCAPE_NIGHT(
        ScreenshotConfig(
            locale = "en",
            orientation = Orientation.LANDSCAPE,
            uiMode = UiMode.NIGHT,
            fontScale = FontSize.NORMAL,
        ),
    ),
    HUGE(
        ScreenshotConfig(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.HUGE,
        ),
    ),
    SMALL(
        ScreenshotConfig(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.SMALL,
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
