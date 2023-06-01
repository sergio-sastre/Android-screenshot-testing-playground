package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.fragment

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.FragmentTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.FragmentTest
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
class RecyclerViewFragmentTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<RecyclerViewFragment>(config = configItem.item)

    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        compareScreenshot(
            fragment = fragmentScenarioConfiguratorRule.fragment,
            name = "RecyclerViewFragment_${configItem.name}_TestParameter",
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class RecyclerViewFragmentTestParameterUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<RecyclerViewFragment>(config = configItem.item)

    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        compareScreenshot(
            fragment = fragmentScenarioConfiguratorRule.fragment,
            name = "RecyclerViewFragment_${configItem.name}_TestParameter",
        )
    }
}
