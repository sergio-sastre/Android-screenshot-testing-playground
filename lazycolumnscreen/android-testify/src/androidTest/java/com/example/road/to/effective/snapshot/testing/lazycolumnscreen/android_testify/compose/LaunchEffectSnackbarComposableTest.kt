package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.compose

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.utils.SnackbarScaffold
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ComposableScreenshotRuleWithConfiguration
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class SnackbarComposableTest {

    @get:Rule(order = 0)
    val disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val screenshotRule = ComposableScreenshotRuleWithConfiguration(exactness = 0.85f)

    @ScreenshotInstrumentation
    @ComposableTest
    @Test
    fun snapComposable() {
        screenshotRule
            .setCompose {
                AppTheme {
                    SnackbarScaffold { snackbarHostState ->
                        ActionNotSupportedSnackbar(
                            snackbarHostState = snackbarHostState,
                            onDismiss = {}
                        )
                    }
                }
            }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "ActionNotSupportedSnackbar"
            )
    }
}
