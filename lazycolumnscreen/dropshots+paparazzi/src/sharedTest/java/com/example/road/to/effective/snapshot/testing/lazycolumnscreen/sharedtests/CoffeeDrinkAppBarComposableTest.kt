package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:dropshots+paparazzi:recordPaparazziDebug
 *  2. Dropshots: ./gradlew :lazycolumnscreen:dropshots+paparazzi:connectedAndroidTest -Pdropshots.record
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:dropshots+paparazzi:verifyPaparazziDebug
 *  2. Dropshots: ./gradlew :lazycolumnscreen:dropshots+paparazzi:connectedAndroidTest
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class CoffeeDrinkAppBarHappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfigForComposable(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontScale = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @HappyPath
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkAppBar_Happy_API_$sdkVersion") {
            AppTheme {
                CoffeeDrinkAppBar("API $sdkVersion")
            }
        }
    }
}

@RunWith(CrossLibraryScreenshotTestRunner::class)
class CoffeeDrinkAppBarUnhappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfigForComposable(
                locale = "en",
                uiMode = UiMode.NIGHT,
                orientation = Orientation.LANDSCAPE,
                fontScale = FontSize.HUGE,
                displaySize = DisplaySize.LARGEST,
            )
        )

    @UnhappyPath
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkAppBar_Unhappy_API_$sdkVersion") {
            AppTheme {
                CoffeeDrinkAppBar("API $sdkVersion")
            }
        }
    }
}
