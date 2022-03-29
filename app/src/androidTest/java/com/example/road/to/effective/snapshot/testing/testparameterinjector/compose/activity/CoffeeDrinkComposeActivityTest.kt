package com.example.road.to.effective.snapshot.testing.testparameterinjector.compose.activity

import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.CoffeeDrinkComposeActivitySnapshotHelper.snapCoffeeDrinkComposeActivity
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.LocaleTestRule

// Shot requires API 26+ for testing Composables
@SdkSuppress(minSdkVersion = 26)
@RunWith(TestParameterInjector::class)
class CoffeeDrinkComposeActivityHappyPathTest(
    @TestParameter val testItem: HappyPathTestItems
) : ScreenshotTest {

    enum class HappyPathTestItems(val configItem: ConfigTestItem) {
        HAPPY(
            ConfigTestItem(
                Orientation.PORTRAIT,
                UiMode.DAY,
                "en",
                FontSize.NORMAL,
                "CoffeeDrinkComposeActivity_HappyPath"
            )
        )
    }

    private val configItem = testItem.configItem

    @get:Rule
    val locale = LocaleTestRule(configItem.locale)

    @get:Rule
    val fontSize = FontSizeTestRule(configItem.fontSize)

    @HappyPath
    @Test
    fun composeActivityHappyPathTest() {
        snapCoffeeDrinkComposeActivity(configItem)
    }

    @HappyPath
    @Test
    fun composeActivityUnhappyPathTest() {
        snapCoffeeDrinkComposeActivity(configItem)
    }
}

// Shot requires API 26+ for testing Composables
@SdkSuppress(minSdkVersion = 26)
@RunWith(TestParameterInjector::class)
class CoffeeDrinkComposeActivityUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItems
) : ScreenshotTest {

    enum class UnhappyPathTestItems(val configItem: ConfigTestItem) {
        UNHAPPY(
            ConfigTestItem(
                Orientation.LANDSCAPE,
                UiMode.NIGHT,
                "en",
                FontSize.HUGE,
                "CoffeeDrinkComposeActivity_UnhappyPath"
            )
        )
    }

    private val configItem = testItem.configItem

    @get:Rule
    val locale = LocaleTestRule(configItem.locale)

    @get:Rule
    val fontSize = FontSizeTestRule(configItem.fontSize)

    @UnhappyPath
    @Test
    fun composeActivityUnhappyPathTest() {
        snapCoffeeDrinkComposeActivity(configItem)
    }
}
