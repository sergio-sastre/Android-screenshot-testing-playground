package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.example.road.to.effective.snapshot.testing.compose.AppTheme
import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkAppBar
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity

object CoffeeDrinkAppBarTestItemSnapshotHelper: ScreenshotTest {

    fun snapCoffeeDrinkAppBar(
        composeTestRule: ComposeTestRule,
        configItem: ConfigTestItem
    ) {
        val activityScenario =
            ActivityScenarioConfigurator.ForComposable()
                .setFontSize(configItem.fontSize)
                .setLocale(configItem.locale)
                .setInitialOrientation(configItem.orientation)
                .setUiMode(configItem.uiMode)
                .launchConfiguredActivity()
                .onActivity {
                    it.setContent {
                        AppTheme {
                            CoffeeDrinkAppBar()
                        }
                    }
                }

        activityScenario.waitForActivity()

        compareScreenshot(rule = composeTestRule, name = configItem.name)

        activityScenario.close()
    }
}
