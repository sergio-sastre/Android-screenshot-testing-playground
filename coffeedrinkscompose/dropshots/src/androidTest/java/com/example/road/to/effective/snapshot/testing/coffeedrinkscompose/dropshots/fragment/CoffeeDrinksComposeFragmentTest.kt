package com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.dropshots.fragment

import androidx.core.os.bundleOf
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.coffeedrinkscompose.CoffeeDrinksFragment
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.framework.DropshotsTest
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
 * Example with fragmentScenarioConfiguratorRule
 */
class CoffeeDrinksComposeFragmentHappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

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

    @HappyPath @DropshotsTest
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.view!!,
            name = "CoffeeDrinksFragment_HappyPath"
        )
    }
}

/**
 * Example with FragmentScenarioConfigurator
 */
class CoffeeDrinksComposeFragmentUnhappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @UnhappyPath @DropshotsTest
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

        dropshots.assertSnapshot(
            view = fragmentScenario.waitForFragment().view!!,
            name = "CoffeeDrinksFragment_UnhappyPath",
        )

        fragmentScenario.close()
    }
}
