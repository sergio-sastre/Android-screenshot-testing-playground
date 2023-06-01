package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.activity

import androidx.test.filters.SdkSuppress
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.DropshotsAPI29Fix
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewActivity
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
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.FragmentTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.FragmentTest
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
class RecyclerViewActivityTestParameterHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) {
    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "RecyclerViewActivity_${configItem.name}_TestParameter"
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class RecyclerViewActivityTestParameterUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) {
    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @UnhappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "RecyclerViewActivity_${configItem.name}_TestParameter"
        )
    }
}
