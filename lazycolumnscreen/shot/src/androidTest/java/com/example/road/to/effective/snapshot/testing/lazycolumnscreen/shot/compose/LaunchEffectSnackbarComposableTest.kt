package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.compose

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.setContent
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.shot.utils.SnackbarScaffold
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest -Precord
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:shot:executeScreenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
@SdkSuppress(minSdkVersion = 26) // ScreenshotTest.compareScreenshot(rule = ...) requires API 26+
class SnackbarComposableTest : ScreenshotTest {

    @get:Rule
    val activityScenarioForComposableRule = ActivityScenarioForComposableRule()

    @ComposableTest
    @Test
    fun snapComposable() {
        activityScenarioForComposableRule.setContent {
            AppTheme {
                SnackbarScaffold { snackbarHostState ->
                    ActionNotSupportedSnackbar(
                        snackbarHostState = snackbarHostState,
                        onDismiss = {}
                    )
                }
            }
        }

        compareScreenshot(
            rule = activityScenarioForComposableRule.composeRule,
            name = "ActionNotSupportedSnackbar",
        )
    }
}
