package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.activity

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.ScreenshotRule
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.android_testify.waitForIdleSync
import sergio.sastre.uitesting.utils.common.FontSize
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
    @get:Rule
    val inAppLocale = InAppLocaleTestRule("en")

    @get:Rule
    val activityScreenshotRule =
        ScreenshotRule(
            configuration = TestifyConfiguration(exactness = 0.85f),
            activityClass = CoffeeDrinksComposeActivity::class.java
        )

    @ScreenshotInstrumentation
    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        activityScreenshotRule
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(name = "CoffeeDrinksComposeActivity_HappyPath")
    }
}

class CoffeeDrinkComposeActivityUnhappyPathTest {

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule
    val inAppLocale = InAppLocaleTestRule("ar_XB")

    @get:Rule
    val systemLocale = SystemLocaleTestRule("en_XA")

    @get:Rule
    val fontSize = FontSizeTestRule(FontSize.LARGEST)

    @get:Rule
    val uiMode = UiModeTestRule(UiMode.NIGHT)

    @get:Rule
    val activityScreenshotRule =
        ScreenshotRule(
            activityClass = CoffeeDrinksComposeActivity::class.java,
            configuration = TestifyConfiguration(
                exactness = 0.85f,
                orientation = SCREEN_ORIENTATION_LANDSCAPE
            )
        )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        activityScreenshotRule
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .waitForIdleSync()
            .assertSame(
                name = "CoffeeDrinksComposeActivity_UnhappyPath",
            )
    }
}
