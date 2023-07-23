package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.fragment

import androidx.core.os.bundleOf
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentScenarioConfigurator
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import sergio.sastre.uitesting.utils.fragmentscenario.waitForFragment

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest -Precord
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with fragmentScenarioConfiguratorRule of AndroidUiTestingUtils
 */
class CoffeeDrinksComposeFragmentHappyPathTest : ScreenshotTest {
    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<CoffeeDrinksFragment>(
            fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
            config = FragmentConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        compareScreenshot(
            fragment = fragmentScenarioConfiguratorRule.fragment,
            name = "CoffeeDrinksFragment_Happy"
        )
    }
}

/**
 * Example with FragmentScenarioConfigurator of AndroidUiTestingUtils
 */
class CoffeeDrinksComposeFragmentUnhappyPathTest : ScreenshotTest {
    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        val fragmentScenario =
            FragmentScenarioConfigurator
                .setLocale("ar_XB")
                .setUiMode(UiMode.NIGHT)
                .setInitialOrientation(Orientation.LANDSCAPE)
                .setFontSize(FontSize.SMALL)
                .setDisplaySize(DisplaySize.SMALL)
                .launchInContainer(
                    fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
                    fragmentClass = CoffeeDrinksFragment::class.java,
                )

        compareScreenshot(
            fragment = fragmentScenario.waitForFragment(),
            name = "CoffeeDrinksFragment_Unhappy",
        )

        fragmentScenario.close()
    }
}
