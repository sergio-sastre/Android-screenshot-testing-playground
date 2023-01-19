package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.karumi.shot.ScreenshotTest
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
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest : ScreenshotTest {
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
    fun snapViewHolder() {
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

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.height,
            name = "MemoriseViewHolder_Happy"
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForView() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForViewRule()
 */
class MemoriseViewHolderUnhappyPathTest : ScreenshotTest {
    @UnhappyPath
    @Test
    fun snapViewHolder() {
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

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = "MemoriseViewHolder_Unhappy"
        )

        activityScenario.close()
    }
}
