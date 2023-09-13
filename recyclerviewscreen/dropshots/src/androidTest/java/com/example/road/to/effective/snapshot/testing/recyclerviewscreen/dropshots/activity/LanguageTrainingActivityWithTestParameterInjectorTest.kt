package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.activity

import androidx.test.filters.SdkSuppress
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class LanguageTrainingActivityTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) {
    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<LanguageTrainingActivity>(configItem.item)

    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "LanguageTrainingActivity_${configItem.name}_TestParameter",
            filePath = "activity",
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class LanguageTrainingActivityTestParameterUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) {
    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<LanguageTrainingActivity>(configItem.item)

    @UnhappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "LanguageTrainingActivity_${configItem.name}_TestParameter",
            filePath = "activity",
        )
    }
}
