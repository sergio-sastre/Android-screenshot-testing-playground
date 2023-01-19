package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
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
import sergio.sastre.uitesting.utils.utils.waitForView

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
        }.itemView

        dropshots.assertSnapshot(
            view = viewHolder,
            name = "MemoriseView_Happy"
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

        val viewHolder = waitForViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = true, activity = activity))
            }
        }

        dropshots.assertSnapshot(
            view = viewHolder,
            name = "MemoriseView_Unhappy"
        )

        activityScenario.close()
    }
}
