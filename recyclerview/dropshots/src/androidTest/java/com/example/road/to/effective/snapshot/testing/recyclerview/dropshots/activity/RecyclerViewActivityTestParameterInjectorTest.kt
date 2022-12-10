package com.example.road.to.effective.snapshot.testing.recyclerview.dropshots.activity

import androidx.test.filters.SdkSuppress
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewActivity
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule

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
class RecyclerViewActivityTestParameterInjectorHappyPathTest(
    @TestParameter val configItem: HappyPathTestItem,
) {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @HappyPath
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "RecyclerViewActivity_${configItem.name}_TestParameterInjector"
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class RecyclerViewActivityTestParameterInjectorUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @UnhappyPath
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "RecyclerViewActivity_${configItem.name}_TestParameterInjector"
        )
    }
}
