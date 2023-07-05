package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.workshop

/**
 * Record:
 * ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pdropshots.record
 *
 * Verify:
 * ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest
 *
 * Execute tests in thi-Pandroid.testInstrumentationRunnerArguments.package=com.example.road.to.es package only by adding the following to the previous command
 * -Pdropshots.record -Pandroid.testInstrumentationRunnerArguments.package=com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.workshop
 *
 * Record:
 * ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pdropshots.record -Pandroid.testInstrumentationRunnerArguments.package=com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.workshop
 *
 * Verify:
 * ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.workshop
 */

import com.dropbox.dropshots.Dropshots
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder.parameterized.wordsInSomeLangsTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

class ActivityScreenshotTest {

    @get:Rule
    val activityScenarioRule =
        ActivityScenarioForViewRule()

    @get:Rule
    val dropshots = Dropshots()

    @Test
    fun snapViewHolder() {
        val layout = activityScenarioRule.inflateAndWaitForIdle(R.layout.training_row)
        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(
                    item = wordsInSomeLangsTrainingItem,
                    languageClickedListener = null,
                )
            }
        }

        dropshots.assertSnapshot(
            view = viewHolder.itemView,
            name = "trainingVH"
        )
    }
}
