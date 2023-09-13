package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.recyclerview

import android.view.View
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import sergio.sastre.uitesting.utils.utils.drawFullScrollableToBitmap

/**
 * Execute the command below to run only RecyclerViewTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class FullRecyclerViewTest {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val fragmentScenarioForActivityRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>()

    @RecyclerViewTest
    @Test
    fun snapFullRecyclerView() {
        val fragmentView = fragmentScenarioForActivityRule.fragment.requireView()
        val recyclerView: View = fragmentView.findViewById(R.id.memoriseList)

        dropshots.assertSnapshot(
            bitmap = recyclerView.drawFullScrollableToBitmap(),
            name = "FullRecyclerView",
            filePath = "recyclerview",
        )
    }
}
