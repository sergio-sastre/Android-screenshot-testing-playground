package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.parameterized

import androidx.test.filters.SdkSuppress
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.ViewHolderTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.utils.testannotations.ViewHolderTest
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
class TrainingViewHolderTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem
) : ScreenshotTest {

    @get:Rule
    val rule = ActivityScenarioForViewRule(config = testItem.item.viewConfig)

    @HappyPath
    @ViewHolderTest
    @Test
    fun snapViewHolder() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = "${testItem.name}_TestParameter", // testItem names are already long
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class TrainingViewHolderTestParameterUnhappyPath(
    @TestParameter val testItem: UnhappyPathTestItem
) : ScreenshotTest {

    @get:Rule
    val rule = ActivityScenarioForViewRule(config = testItem.item.viewConfig)

    @UnhappyPath
    @ViewHolderTest
    @Test
    fun snapViewHolder() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = "${testItem.name}_TestParameter", // testItem names are already long
        )
    }
}
