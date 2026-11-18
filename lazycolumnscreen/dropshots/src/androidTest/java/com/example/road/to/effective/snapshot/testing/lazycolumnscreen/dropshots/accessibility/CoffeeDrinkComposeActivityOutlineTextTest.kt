package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.accessibility

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
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
 *    ./gradlew :lazycolumnscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class CoffeeDrinkComposeActivityOutlineTextTest {

    @get:Rule(order = 1)
    val outlineTextTestRule =
        OutlineTextTestRule(OutlineText.ENABLED)

    @get:Rule(order = 2)
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<CoffeeDrinksComposeActivity>()

    @get:Rule(order = 3)
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

    @AccessibilityTest
    @Test
    fun snapActivity() {
        val bitmap = activityScenarioForActivityRule.rootView.drawToBitmapWithElevation()
        dropshots.assertSnapshot(
            bitmap = bitmap,
            name = "CoffeeDrinksComposeActivity_OutlineText",
            filePath = "accessibility",
        )
    }
}
