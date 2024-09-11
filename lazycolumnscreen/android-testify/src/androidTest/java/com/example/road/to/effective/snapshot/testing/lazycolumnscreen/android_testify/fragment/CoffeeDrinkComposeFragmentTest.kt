package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.fragment

import androidx.core.os.bundleOf
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.screenshotscenario.ScreenshotScenarioRuleForFragment
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.waitForIdleSync
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeFragmentHappyPathTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = ViewConfigItem(
            locale = "en",
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            displaySize = DisplaySize.NORMAL,
            orientation = Orientation.PORTRAIT
        )
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRuleForFragment(
        fragmentClass = CoffeeDrinksFragment::class.java,
        fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
        configuration = TestifyConfiguration(exactness = 0.85f),
    )

    @ScreenshotInstrumentation
    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        screenshotRule
            .withScenario(viewScenarioRule.activityScenario)
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(name = "CoffeeDrinksComposeFragment_HappyPath")
    }
}

class CoffeeDrinkComposeFragmentUnhappyPathTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = ViewConfigItem(
            locale = "ar_XB",
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.SMALL,
            displaySize = DisplaySize.SMALL,
            orientation = Orientation.LANDSCAPE
        )
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRuleForFragment(
        fragmentClass = CoffeeDrinksFragment::class.java,
        fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
        configuration = TestifyConfiguration(exactness = 0.85f),
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        screenshotRule
            .withScenario(viewScenarioRule.activityScenario)
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(
                name = "CoffeeDrinksComposeFragment_UnhappyPath"
            )
    }
}
