package com.example.road.to.effective.snapshot.testing.testparameterinjector.compose.composeitem

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.CoffeeDrinkListTestItemObjectMother
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.CoffeeDrinkListItemSnapshotHelper
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Shot requires API 26+ for testing Composables
@SdkSuppress(minSdkVersion = 26)
@RunWith(TestParameterInjector::class)
class CoffeeDrinkListComposableTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    enum class CoffeeDrinkUnhappyPath(val coffeeDrinkItem: CoffeeDrinkListTestItem) {
        HAPPY(CoffeeDrinkListTestItemObjectMother.happyPath())
    }

    enum class CoffeeDrinkHappyPath(val coffeeDrinkItem: CoffeeDrinkListTestItem) {
        UNHAPPY(CoffeeDrinkListTestItemObjectMother.unhappyPath())
    }

    @HappyPath
    @Test
    fun snapCoffeeDrinkListHappyPathTest(@TestParameter testItem: CoffeeDrinkHappyPath) {
        CoffeeDrinkListItemSnapshotHelper.snapCoffeeDrinkListItem(
            composeTestRule,
            testItem.coffeeDrinkItem
        )
    }

    @UnhappyPath
    @Test
    fun snapCoffeeDrinkListUnhappyPathTest(@TestParameter testItem: CoffeeDrinkUnhappyPath) {
        CoffeeDrinkListItemSnapshotHelper.snapCoffeeDrinkListItem(
            composeTestRule,
            testItem.coffeeDrinkItem
        )
    }
}
