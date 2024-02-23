package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.sdkVersion
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:android-testify+paparazzi:recordPaparazziDebug
 *  2. Testify:   ./gradlew :lazycolumnscreen:android-testify+paparazzi:screenshotRecord
 *     Testify via gradle manages devices (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *                ./gradlew :lazycolumnscreen:android-testify+paparazzi:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:android-testify+paparazzi:verifyPaparazziDebug
 *  2. Testify:   ./gradlew :lazycolumnscreen:android-testify+paparazzi:screenshotTest
 *     Testify via gradle manages devices (copy recorded screenshots + assert):
 *          - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *               ./gradlew :lazycolumnscreen:android-testify+paparazzi:copyScreenshots -Pdevices=pixel3api30
 *          - Assert
 *               ./gradlew :lazycolumnscreen:android-testify+paparazzi:pixel3api30DebugAndroidTest -PuseTestStorage
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

    @CrossLibraryScreenshot
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
                locale = "ar_XB",
                uiMode = UiMode.NIGHT,
                orientation = Orientation.LANDSCAPE,
                fontScale = FontSize.HUGE,
                displaySize = DisplaySize.LARGEST,
            )
        )


    @CrossLibraryScreenshot
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "CoffeeDrinkAppBar_Unhappy_API_$sdkVersion") {
            AppTheme {
                CoffeeDrinkAppBar("API $sdkVersion")
            }
        }
    }
}
