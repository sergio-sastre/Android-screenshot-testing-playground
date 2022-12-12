package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.activity

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewActivity
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
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
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @HappyPath
    @Test
    fun snapActivity() {
        val activity = activityScenarioForActivityRule.activity
        val activityView = activity.window.decorView

        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "RecyclerViewActivity_${configItem.name}_TestParameterInjector"
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class RecyclerViewActivityTestParameterInjectorUnhappyPathTest(
    @TestParameter val configItem: UnhappyPathTestItem,
) : ScreenshotTest {

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<RecyclerViewActivity>(configItem.item)

    @UnhappyPath
    @Test
    fun snapActivity() {
        val activity = activityScenarioForActivityRule.activity
        val activityView = activity.window.decorView

        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "RecyclerViewActivity_${configItem.name}_TestParameterInjector"
        )
    }
}
