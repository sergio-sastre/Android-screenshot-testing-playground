package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.ScreenshotConfig
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

class CoffeeDrinkAppBarComposableHappyPathTest {

    @get:Rule
    val screenshotTestRule =
        SharedTestRule(
            config = ScreenshotConfig(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontScale = FontSize.NORMAL,
            )
        )

    @Test
    fun snapComposable() {
        screenshotTestRule.snapshot(name = "CoffeeDrinkAppBar_HappyPath") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}

class CoffeeDrinkAppBarComposableHappyUnhappyPathTest {

    @get:Rule
    val screenshotTestRule =
        SharedTestRule(
            config = ScreenshotConfig(
                locale = "en",
                uiMode = UiMode.NIGHT,
                orientation = Orientation.LANDSCAPE,
                fontScale = FontSize.HUGE,
            )
        )

    @Test
    fun snapComposable() {
        screenshotTestRule.snapshot(name = "CoffeeDrinkAppBar_UnhappyPath") {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }
    }
}
