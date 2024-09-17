package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.fragment

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.android_testify.screenshotscenario.ScreenshotScenarioRuleForFragment
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.waitForIdleSync
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
@RunWith(Parameterized::class)
class LanguageTrainingFragmentParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
                HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = testItem.item
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRuleForFragment(
        fragmentClass = LanguageTrainingFragment::class.java,
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
            .assertSame(name = "LanguageTrainingFragment_${testItem.name}_Parameterized")
    }
}

@RunWith(Parameterized::class)
class LanguageTrainingFragmentParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = testItem.item
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRuleForFragment(
        fragmentClass = LanguageTrainingFragment::class.java,
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
                name = "LanguageTrainingFragment_${testItem.name}_Parameterized"
            )
    }
}
