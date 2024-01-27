package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.fragment

import androidx.core.os.bundleOf
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.TestifyFeatures.GenerateDiffs
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForFragment
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.fragmentscenario.FragmentConfigItem
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
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage
 *
 * WARNING: filtering tests by custom annotation not working with Gradle Managed Devices
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeFragmentHappyPathTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val activityScreenshotRule =
        ScreenshotRuleWithConfigurationForFragment(
            exactness = 0.85f,
            fragmentClass = CoffeeDrinksFragment::class.java,
            fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
            config = FragmentConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
                orientation = Orientation.PORTRAIT
            ),
        )

    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        activityScreenshotRule
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "CoffeeDrinksComposeFragment_HappyPath"
            )
    }
}

class CoffeeDrinkComposeFragmentUnhappyPathTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val activityScreenshotRule =
        ScreenshotRuleWithConfigurationForFragment(
            exactness = 0.85f,
            fragmentClass = CoffeeDrinksFragment::class.java,
            fragmentArgs = bundleOf("coffee_shop_name" to "MyCoffeeShop"),
            config = FragmentConfigItem(
                locale = "ar_XB",
                uiMode = UiMode.NIGHT,
                fontSize = FontSize.SMALL,
                displaySize = DisplaySize.SMALL,
                orientation = Orientation.LANDSCAPE
            ),
        )

    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        activityScreenshotRule
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "CoffeeDrinksComposeFragment_UnhappyPath"
            )
    }
}
