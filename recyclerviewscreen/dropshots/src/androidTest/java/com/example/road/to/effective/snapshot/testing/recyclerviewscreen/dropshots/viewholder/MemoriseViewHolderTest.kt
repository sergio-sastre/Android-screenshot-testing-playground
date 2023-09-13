package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.inflateAndWaitForIdle
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = ViewConfigItem(
                locale = "en",
                uiMode = UiMode.DAY,
                orientation = Orientation.PORTRAIT,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @HappyPath
    @ViewHolderTest
    @Test
    fun snapMemoriseViewHolderHappyPath() {
        val activity = activityScenarioForViewRule.activity
        val layout = activityScenarioForViewRule.inflateAndWaitForIdle(R.layout.memorise_row)

        val viewHolder = waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = false, activity = activity))
            }
        }

        dropshots.assertSnapshot(
            view = viewHolder.itemView,
            name = "MemoriseView_Happy",
            filePath = "viewholder",
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForView() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForViewRule()
 */
class MemoriseViewHolderUnhappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @UnhappyPath
    @ViewHolderTest
    @Test
    fun snapMemoriseViewHolderUnhappyPath() {
        val activityScenario = ActivityScenarioConfigurator.ForView()
            .setInitialOrientation(Orientation.LANDSCAPE)
            .setLocale("en_XA")
            .setUiMode(UiMode.NIGHT)
            .setFontSize(FontSize.HUGE)
            .setDisplaySize(DisplaySize.LARGEST)
            .launchConfiguredActivity()

        val activity = activityScenario.waitForActivity()

        val layout = activity.inflateAndWaitForIdle(R.layout.memorise_row)

        val viewHolder = waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = true, activity = activity))
            }
        }

        dropshots.assertSnapshot(
            view = viewHolder.itemView,
            name = "MemoriseView_Unhappy",
            filePath = "viewholder",
        )

        activityScenario.close()
    }
}
