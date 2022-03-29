package com.example.road.to.effective.snapshot.testing.parameterized.composeitem

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.CoffeeDrinkAppBarTestItemObjectMother
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.CoffeeDrinkAppBarTestItemSnapshotHelper
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@SdkSuppress(minSdkVersion = 26)
@RunWith(Parameterized::class)
class CoffeeDrinkAppBarHappyPathTest(
    private val testItem: ConfigTestItem
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<ConfigTestItem> =
            arrayOf(
                CoffeeDrinkAppBarTestItemObjectMother.happyPath()
            )
    }

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @HappyPath
    @Test
    fun snapComposableHappyPathTest() {
        CoffeeDrinkAppBarTestItemSnapshotHelper.snapCoffeeDrinkAppBar(composeTestRule, testItem)
    }
}

@SdkSuppress(minSdkVersion = 26)
@RunWith(Parameterized::class)
class CoffeeDrinkAppBarUnhappyPathTest(
    private val testItem: ConfigTestItem
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<ConfigTestItem> =
            arrayOf(
                CoffeeDrinkAppBarTestItemObjectMother.unhappyPath()
            )
    }

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @UnhappyPath
    @Test
    fun snapComposableUnhappyPathTest() {
        CoffeeDrinkAppBarTestItemSnapshotHelper.snapCoffeeDrinkAppBar(composeTestRule, testItem)
    }
}

