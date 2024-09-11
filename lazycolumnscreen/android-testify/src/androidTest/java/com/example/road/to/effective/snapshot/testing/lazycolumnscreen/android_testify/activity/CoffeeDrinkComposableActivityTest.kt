package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.activity

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.waitForIdleSync
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.InAppLocaleTestRule
import sergio.sastre.uitesting.utils.testrules.locale.SystemLocaleTestRule
import sergio.sastre.uitesting.utils.testrules.uiMode.UiModeTestRule

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeActivityHappyPathTest {

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule(order = 0)
    val inAppLocale = InAppLocaleTestRule("en")

    @get:Rule(order = 1)
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<CoffeeDrinksComposeActivity>()

    @get:Rule(order = 2)
    val screenshotRule =
        ScreenshotScenarioRule(
            configuration = TestifyConfiguration(exactness = 0.85f)
        )

    @ScreenshotInstrumentation
    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        screenshotRule
            .withScenario(activityScenarioForActivityRule.activityScenario)
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(name = "CoffeeDrinksComposeActivity_HappyPath")
    }
}

class CoffeeDrinkComposeActivityUnhappyPathTest {

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule(order = 0)
    val inAppLocale = InAppLocaleTestRule("ar_XB")

    @get:Rule(order = 1)
    val systemLocale = SystemLocaleTestRule("en_XA")

    @get:Rule(order = 2)
    val fontSize = FontSizeTestRule(FontSize.LARGEST)

    @get:Rule(order = 3)
    val uiMode = UiModeTestRule(UiMode.NIGHT)

    @get:Rule(order = 4)
    val activityScenarioRule =
        activityScenarioForActivityRule<CoffeeDrinksComposeActivity>(
            config = ActivityConfigItem(orientation = Orientation.LANDSCAPE),
        )

    @get:Rule(order = 5)
    val screenshotRule =
        ScreenshotScenarioRule(
            configuration = TestifyConfiguration(exactness = 0.85f)
        )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        screenshotRule
            .withScenario(activityScenarioRule.activityScenario)
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(
                name = "CoffeeDrinksComposeActivity_UnhappyPath",
            )
    }
}
