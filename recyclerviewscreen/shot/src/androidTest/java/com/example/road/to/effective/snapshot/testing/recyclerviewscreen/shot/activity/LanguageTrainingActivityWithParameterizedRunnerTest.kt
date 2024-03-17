package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.activity

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule

/**
 * Execute the command below to run only ActivityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ActivityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+, throwing
 * java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder error in lower APIs
 */
@RunWith(Parameterized::class)
class LanguageTrainingActivityParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
                HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<LanguageTrainingActivity>(testItem.item)

    @HappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        val activity = activityScenarioForActivityRule.activity
        val activityView = activity.window.decorView

        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "LanguageTrainingActivity_${testItem.name}_Parameterized"
        )
    }
}

@RunWith(Parameterized::class)
class LanguageTrainingActivityParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<LanguageTrainingActivity>(testItem.item)

    @UnhappyPath
    @ActivityTest
    @Test
    fun snapActivity() {
        val activity = activityScenarioForActivityRule.activity
        val activityView = activity.window.decorView

        compareScreenshot(
            activity = activity,
            heightInPx = activityView.measuredHeight,
            widthInPx = activityView.measuredWidth,
            name = "LanguageTrainingActivity_${testItem.name}_Parameterized"
        )
    }
}
