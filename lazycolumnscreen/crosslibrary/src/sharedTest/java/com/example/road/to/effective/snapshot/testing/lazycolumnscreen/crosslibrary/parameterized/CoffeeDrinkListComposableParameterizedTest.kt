package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.crosslibrary.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :lazycolumnscreen:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :lazycolumnscreen:crosslibrary:screenshotRecord -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *                ./gradlew :lazycolumnscreen:crosslibrary:pixel3api30DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage -PrecordModeGmd
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :lazycolumnscreen:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :lazycolumnscreen:crosslibrary:screenshotTest -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (copy recorded screenshots + assert):
 *          - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *               ./gradlew :lazycolumnscreen:crosslibrary:copyScreenshots -Pdevices=pixel3api30
 *          - Assert
 *               ./gradlew :lazycolumnscreen:crosslibrary:pixel3api30DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/lazycolumnscreen/crosslibrary/parameterized
 */
@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class CoffeeDrinkListComposableParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(config = testItem.item)

    @CrossLibraryScreenshot
    @HappyPath
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(
            name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized_API_$sdkVersion"
        ) {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}

@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class CoffeeDrinkListComposableParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(config = testItem.item)

    @CrossLibraryScreenshot
    @UnhappyPath
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(
            name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized_API_$sdkVersion"
        ) {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
