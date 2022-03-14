package com.example.road.to.effective.snapshot.testing.utils.snapshotter
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewActivity
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity

object RecyclerViewActivitySnapshotHelper : ScreenshotTest {

    fun snapRecyclerViewActivity(testItem: ConfigTestItem) {
        val activity =
            ActivityScenarioConfigurator.ForActivity()
                .setOrientation(testItem.orientation)
                .setUiMode(testItem.uiMode)
                .launch(RecyclerViewActivity::class.java)
                .waitForActivity()

        compareScreenshot(
            activity = activity,
            name = testItem.name
        )
    }
}
