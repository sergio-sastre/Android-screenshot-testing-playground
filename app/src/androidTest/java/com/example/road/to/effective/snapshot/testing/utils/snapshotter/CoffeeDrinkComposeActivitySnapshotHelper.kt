package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkComposeActivity
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity

object CoffeeDrinkComposeActivitySnapshotHelper: ScreenshotTest {

    fun snapCoffeeDrinkComposeActivity(
        configItem: ConfigTestItem
    ) {
        val activityScenario = ActivityScenarioConfigurator.ForActivity()
            .setOrientation(configItem.orientation)
            .setUiMode(configItem.uiMode)
            .launch(CoffeeDrinkComposeActivity::class.java)

        val activity = activityScenario.waitForActivity()

        compareScreenshot(activity = activity, name = configItem.name)

        activityScenario.close()
    }
}
