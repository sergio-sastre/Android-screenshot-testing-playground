package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.compose

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.utils.SnackbarScaffold
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.utils.setContent
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:dropshots:recordScreenshots -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class SnackbarComposableTest {

    @get:Rule
    val dropshots =
        Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = activityScenarioForComposableRule.composeView,
            name = "ActionNotSupportedSnackbar",
            filePath = "compose",
        )
    }
}
