package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.bitmap

import androidx.core.os.bundleOf
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksFragment
import com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import sergio.sastre.uitesting.utils.utils.drawToBitmap
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Execute the command below to run only BitmapTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.BitmapTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with fragmentScenarioConfiguratorRule of AndroidUiTestingUtils
 */
class CoffeeDrinksComposeFragmentToBitmapTest {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

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

    // For API < 26, drawToBitmapWithElevation defaults to Canvas. Thus, draws no elevation
    @BitmapTest
    @Test
    fun snapFragmentWithPixelCopy() {
        dropshots.assertSnapshot(
            bitmap = fragmentScenarioConfiguratorRule.fragment.drawToBitmapWithElevation(),
            name = "CoffeeDrinksFragment_BitmapWithElevation",
            filePath = "bitmap",
        )
    }

    @BitmapTest
    @Test
    fun snapFragmentWithCanvas() {
        dropshots.assertSnapshot(
            bitmap = fragmentScenarioConfiguratorRule.fragment.drawToBitmap(),
            name = "CoffeeDrinksFragment_BitmapWithoutElevation",
            filePath = "bitmap",
        )
    }
}
