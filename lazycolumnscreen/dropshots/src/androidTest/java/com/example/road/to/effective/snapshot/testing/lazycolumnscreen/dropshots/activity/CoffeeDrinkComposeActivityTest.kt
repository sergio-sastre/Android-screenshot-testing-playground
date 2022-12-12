package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.dropshots.activity

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinksComposeActivity
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.activityscenario.ActivityConfigItem
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.activityScenarioForActivityRule
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.LocaleTestRule
import sergio.sastre.uitesting.utils.testrules.locale.SystemLocaleTestRule
import sergio.sastre.uitesting.utils.utils.waitForActivity

/**
 * Example with ActivityScenarioForActivityRule()
 */
class CoffeeDrinkComposeActivityHappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForActivityRule =
        activityScenarioForActivityRule<CoffeeDrinksComposeActivity>(
            config = ActivityConfigItem(
                systemLocale = "en",
                orientation = Orientation.PORTRAIT,
                uiMode = UiMode.DAY,
                fontSize = FontSize.NORMAL,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @HappyPath
    @Test
    fun snapActivity() {
        dropshots.assertSnapshot(
            activity = activityScenarioForActivityRule.activity,
            name = "CoffeeDrinksComposeActivity_HappyPath"
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForActivity()
 *
 * This is an alternative if we cannot use ActivityScenarioForActivityRule(), or we want to use
 * in-app Locale, which ActivityScenarioForActivityRule does not support
 */
class CoffeeDrinkComposeActivityUnhappyPathTest {

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    // WARNING: in-app Locale prevails over SystemLocale when screenshot testing your app
    @get:Rule
    val appLocale = LocaleTestRule("ar_XB")

    @get:Rule
    val locale = SystemLocaleTestRule("en_XA")

    @get:Rule
    val fontSize = FontSizeTestRule(FontSize.HUGE)

    @UnhappyPath
    @Test
    fun snapActivity() {
        val activityScenario = ActivityScenarioConfigurator.ForActivity()
            .setOrientation(Orientation.LANDSCAPE)
            .setUiMode(UiMode.NIGHT)
            .launch(CoffeeDrinksComposeActivity::class.java)

        dropshots.assertSnapshot(
            activity = activityScenario.waitForActivity(),
            name = "CoffeeDrinksComposeActivity_Unhappy",
        )

        activityScenario.close()
    }
}
