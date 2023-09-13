package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder.parameterized

import androidx.test.filters.SdkSuppress
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
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
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
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
class TrainingViewHolderTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem
) {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = viewHolder.itemView,
            name = "${testItem.name}_TestParameter", // testItem names are already long
            filePath = "viewholder",
        )
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class TrainingViewHolderTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem
) {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = viewHolder.itemView,
            name = "${testItem.name}_TestParameter", // testItem names are already long
            filePath = "viewholder",
        )
    }
}
