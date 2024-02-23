package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.sdkVersion
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

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
