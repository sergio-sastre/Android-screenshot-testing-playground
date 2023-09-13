package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkItem
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.R
import sergio.sastre.uitesting.utils.common.DisplaySize

import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable

enum class HappyPathTestItem(val item: ScreenshotConfigForComposable) {
    PORTRAIT(
        ScreenshotConfigForComposable(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    )
}

enum class UnhappyPathTestItem(val item: ScreenshotConfigForComposable) {
    LANDSCAPE_NIGHT(
        ScreenshotConfigForComposable(
            locale = "en",
            orientation = Orientation.LANDSCAPE,
            uiMode = UiMode.NIGHT,
            fontScale = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
        ),
    ),
    HUGE(
        ScreenshotConfigForComposable(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.HUGE,
            displaySize = DisplaySize.LARGEST,
        ),
    ),
    SMALL(
        ScreenshotConfigForComposable(
            locale = "en",
            orientation = Orientation.PORTRAIT,
            uiMode = UiMode.DAY,
            fontScale = FontSize.SMALL,
            displaySize = DisplaySize.SMALL,
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
