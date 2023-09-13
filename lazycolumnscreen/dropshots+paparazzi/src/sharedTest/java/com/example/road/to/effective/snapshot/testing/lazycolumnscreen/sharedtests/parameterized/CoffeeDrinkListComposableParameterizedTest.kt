package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

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
