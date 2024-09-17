package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder

import android.view.ViewGroup
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.waitForIdleSync
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with ActivityTestRules of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = ViewConfigItem(
            uiMode = UiMode.DAY,
            locale = "en",
            orientation = Orientation.PORTRAIT,
        ),
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(exactness = 0.85f),
    )

    @ScreenshotInstrumentation
    @HappyPath
    @ViewHolderTest
    @Test
    fun snapMemoriseViewHolderHappyPath() {
        val container = viewScenarioRule.inflateAndWaitForIdle(R.layout.memorise_row) as ViewGroup
        val layout = waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = container,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(
                    generateMemoriseItem(
                        rightAligned = false,
                        activity = viewScenarioRule.activity
                    )
                )
            }
        }
        screenshotRule
            .withScenario(viewScenarioRule.activityScenario)
            .setScreenshotViewProvider { layout.itemView }
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(name = "MemoriseViewHolderHappy")
    }
}

class MemoriseViewHolderUnhappyPathTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var viewScenarioRule = ActivityScenarioForViewRule(
        config = ViewConfigItem(
            uiMode = UiMode.NIGHT,
            locale = "en_XA",
            fontSize = FontSize.LARGEST,
            displaySize = DisplaySize.LARGEST,
            orientation = Orientation.LANDSCAPE,
        ),
    )

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(exactness = 0.85f),
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ViewHolderTest
    @Test
    fun snapMemoriseViewHolderHappyPath() {
        val container = viewScenarioRule.inflateAndWaitForIdle(R.layout.memorise_row) as ViewGroup
        val layout = waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = container,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(
                    generateMemoriseItem(
                        rightAligned = false,
                        activity = viewScenarioRule.activity
                    )
                )
            }
        }
        screenshotRule
            .withScenario(viewScenarioRule.activityScenario)
            .setScreenshotViewProvider { layout.itemView }
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(name = "MemoriseViewHolderUnhappy")
    }
}
