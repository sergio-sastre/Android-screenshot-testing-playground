package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.recyclerview

import android.view.View
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.utils.drawFullScrollableToBitmap
import com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule

/**
 * Execute the command below to run only RecyclerViewTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.RecyclerViewTest
 */
class FullRecyclerViewTest : ScreenshotTest {

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>()

    @RecyclerViewTest
    @Test
    fun snapFullRecyclerView() {
        val fragmentView = fragmentScenarioConfiguratorRule.fragment.requireView()
        val recyclerView: View = fragmentView.findViewById(R.id.memoriseList)

        compareScreenshot(
            bitmap = recyclerView.drawFullScrollableToBitmap(),
            name = "FullRecyclerView"
        )
    }
}
