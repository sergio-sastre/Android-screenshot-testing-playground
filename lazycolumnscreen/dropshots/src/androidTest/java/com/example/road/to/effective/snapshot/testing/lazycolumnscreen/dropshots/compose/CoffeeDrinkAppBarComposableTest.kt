package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.compose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.setContent
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.waitForComposeView
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/**
 * Example with ActivityScenarioForComposableRule()
 */
class CoffeeDrinkAppBarHappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForComposableRule =
        ActivityScenarioForComposableRule(
            config = ComposableConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @HappyPath
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                CoffeeDrinkAppBar()
            }
        }

        dropshots.assertSnapshot(
            view = activityScenarioForComposableRule.activity.waitForComposeView(),
            name = "CoffeeDrinkAppBar_Happy",
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForComposable()
 * This is an alternative if we cannot use ActivityScenarioForComposableRule()
 */
class CoffeeDrinkAppBarUnhappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @UnhappyPath
    @Test
    fun snapComposable() {
        val activityScenario =
            ActivityScenarioConfigurator.ForComposable()
                .setLocale("ar_XB")
                .setUiMode(UiMode.NIGHT)
                .setInitialOrientation(Orientation.LANDSCAPE)
                .setFontSize(FontSize.HUGE)
                .setDisplaySize(DisplaySize.LARGER)
                .launchConfiguredActivity()
                .onActivity {
                    it.setContent {
                        AppTheme {
                            CoffeeDrinkAppBar()
                        }
                    }
                }

        dropshots.assertSnapshot(
            view = activityScenario.waitForActivity().waitForComposeView(),
            name = "CoffeeDrinkAppBar_Unhappy",
        )

        activityScenario.close()
    }
}
