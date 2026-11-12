package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.accessibility

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineText
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineTextTestRule
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class LanguageTrainingActivityOutlineTextTest {

    @get:Rule(order = 1)
    val outlineTextTestRule =
        OutlineTextTestRule(OutlineText.ENABLED)

    @get:Rule(order = 2)
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<LanguageTrainingActivity>()

    @get:Rule(order = 3)
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @AccessibilityTest
    @Test
    fun snapActivity() {
        val bitmap = activityScenarioForActivityRule.rootView.drawToBitmapWithElevation()
        dropshots.assertSnapshot(
            bitmap = bitmap,
            name = "LanguageTrainingActivity_OutlineText",
            filePath = "accessibility",
        )
    }
}
