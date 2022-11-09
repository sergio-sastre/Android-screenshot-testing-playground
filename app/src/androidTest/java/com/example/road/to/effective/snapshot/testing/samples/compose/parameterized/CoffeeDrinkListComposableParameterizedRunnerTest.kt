package com.example.road.to.effective.snapshot.testing.samples.compose.parameterized

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.compose.AppTheme
import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.setContent
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments). Therefore, we need to create 2 different classes to separate @UnhappyPath and
 * @HappyPath tests
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+
 */
@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(testItem.item)

    @HappyPath
    @Test
    fun snapCoffeeDrinkList() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized"
        )
    }
}

@SdkSuppress(minSdkVersion = 26) // Shot requires API 26+ for testing Composables
@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule(testItem.item)

    @HappyPath
    @Test
    fun snapCoffeeDrinkList() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkList(coffeeDrink = coffeeDrink)
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "CoffeeDrinkListComposable_${testItem.name}_Parameterized"
        )
    }
}
