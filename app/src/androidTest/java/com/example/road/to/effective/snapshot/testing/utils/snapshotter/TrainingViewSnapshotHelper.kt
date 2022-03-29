package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.parameterized.trainingitem.TrainingTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.utils.inflate
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForView

object TrainingViewSnapshotHelper : ScreenshotTest {

    fun snapTrainingViewHolder(testItem: TrainingTestItem) {
        val activityScenario =
            ActivityScenarioConfigurator.ForView()
                .setFontSize(testItem.fontScale)
                .setLocale(testItem.locale)
                .setUiMode(testItem.uiMode)
                .setInitialOrientation(Orientation.PORTRAIT)
                .launchConfiguredActivity()

        val activity = activityScenario.waitForActivity()

        val layout = waitForView {
            activity.inflate(R.layout.training_row)
        }

        val view = waitForView {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = view,
            heightInPx = layout.height,
            widthInPx = testItem.viewWidth.widthInPx,
            name = testItem.testName
        )

        activityScenario.close()
    }
}
