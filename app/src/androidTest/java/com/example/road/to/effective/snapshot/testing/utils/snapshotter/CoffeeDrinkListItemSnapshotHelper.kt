package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.example.road.to.effective.snapshot.testing.compose.AppTheme
import com.example.road.to.effective.snapshot.testing.compose.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.testparameterinjector.compose.composeitem.CoffeeDrinkListTestItem
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.utils.waitForActivity

object CoffeeDrinkListItemSnapshotHelper {

    fun snapCoffeeDrinkListItem(
        composeTestRule: ComposeTestRule,
        testItem: CoffeeDrinkListTestItem
    ) {
        val configItem = testItem.configTestItem
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
                            CoffeeDrinkList(
                                coffeeDrink = testItem.coffeeDrink
                            )
                        }
                    }
                }

        activityScenario.waitForActivity()

        CoffeeDrinkAppBarTestItemSnapshotHelper.compareScreenshot(
            rule = composeTestRule,
            name = configItem.name
        )

        activityScenario.close()
    }
}
