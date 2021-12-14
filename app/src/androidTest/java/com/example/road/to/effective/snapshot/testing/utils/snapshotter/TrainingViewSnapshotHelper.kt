package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.parameterized.trainingitem.TrainingTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.utils.inflate
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import sergio.sastre.fontsize.FontSizeActivityScenario

object TrainingViewSnapshotHelper : ScreenshotTest {

    fun snapTrainingViewHolder(testItem: TrainingTestItem) {
        val activity = FontSizeActivityScenario.launchWith(testItem.fontScale)
            .waitForActivity()

        val view = waitForView {
            val layout = activity.inflate(R.layout.training_row)
            TrainingViewHolder(layout).apply {
                bind(item = testItem.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = view,
            heightInPx = 600,
            widthInPx = testItem.viewWidth.widthInPx,
            name = testItem.testName
        )
    }

}