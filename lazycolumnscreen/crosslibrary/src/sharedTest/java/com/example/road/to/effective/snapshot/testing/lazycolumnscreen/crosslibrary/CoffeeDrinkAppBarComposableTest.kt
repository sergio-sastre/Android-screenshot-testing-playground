package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfig
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :lazycolumnscreen:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :lazycolumnscreen:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/lazycolumnscreen/crosslibrary
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class CoffeeDrinkAppBarHappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfig(
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
            config = ScreenshotConfig(
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
