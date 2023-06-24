package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.workshop

/**
 * Record:
 * ./gradlew :recyclerviewscreen:shot:executeScreenshotTest -Precord
 *
 * Verify:
 * ./gradlew :recyclerviewscreen:shot:executeScreenshotTest
 *
 * Execute tests in this package only by adding the following to the previous command
 * -Pandroid.testInstrumentationRunnerArguments.package=com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.workshop
 */

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.parameterized.UnhappyPathTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.parameterized.wordsInSomeLangsTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

@RunWith(Parameterized::class)
class ParameterizedScreenshotTest(
    private val testItem: UnhappyPathTestItem,
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioRule =
        ActivityScenarioForViewRule(config = testItem.item.viewConfig)

    @Test
    fun snapActivity() {
        val layout = activityScenarioRule.inflateAndWaitForIdle(R.layout.training_row)
        val viewHolder =
            waitForMeasuredViewHolder {
                TrainingViewHolder(layout).apply {
                    bind(
                        item = wordsInSomeLangsTrainingItem,
                        languageClickedListener = null,
                    )
                }
            }

        compareScreenshot(
            view = viewHolder.itemView,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = testItem.name
        )
    }
}