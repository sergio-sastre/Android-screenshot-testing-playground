package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.accessibility

import android.app.Activity
import android.view.View
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForActivityRule
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.testrules.accessibility.colorcontrast.ColorContrast
import sergio.sastre.uitesting.utils.testrules.accessibility.colorcontrast.ColorContrastTestRule
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.rootView

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
val <A : Activity> ActivityScenarioForActivityRule<A>.rootView: View
    get() = activityScenario.rootView

@RunWith(Parameterized::class)
class LanguageTrainingActivityColorContrastTest(
    private val colorContrastOption: ColorContrast,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<ColorContrast> =
            ColorContrast.entries.toTypedArray()
    }

    @get:Rule(order = 1)
    val colorContrastTestRule =
        ColorContrastTestRule(colorContrastOption)

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
            name = "LanguageTrainingActivity_${colorContrastOption.name}_Parameterized",
            filePath = "accessibility",
        )
    }
}