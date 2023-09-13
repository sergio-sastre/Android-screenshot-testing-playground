package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.fragment

import androidx.test.filters.SdkSuppress
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
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
class LanguageTrainingFragmentTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>(config = configItem.item)

    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.requireView(),
            name = "RecyclerViewFragment_${configItem.name}_TestParameter",
            filePath = "fragment",
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class LanguageTrainingFragmentTestParameterIUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
)  {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>(config = configItem.item)

    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.requireView(),
            name = "RecyclerViewFragment_${configItem.name}_TestParameter",
            filePath = "fragment",
        )
    }
}
