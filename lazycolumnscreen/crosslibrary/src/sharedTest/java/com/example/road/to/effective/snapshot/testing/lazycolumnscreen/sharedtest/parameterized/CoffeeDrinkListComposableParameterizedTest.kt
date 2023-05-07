package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtest.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedScreenshotTestAndroidJUnit4

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:recordPaparazziDebug
 *  2. Shot: ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTests -Precord -PscreenshotLibrary=shot
 *  3. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:crosslibrary:verifyPaparazziDebug
 *  2. Shot: ./gradlew :lazycolumnscreen:crosslibrary:executeScreenshotTests -PscreenshotLibrary=shot
 *  3. Dropshots: ./gradlew :lazycolumnscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 */
@RunWith(ParameterizedScreenshotTestAndroidJUnit4::class)
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
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}

@RunWith(ParameterizedScreenshotTestAndroidJUnit4::class)
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
        screenshotRule.snapshot(name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized") {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }
    }
}
