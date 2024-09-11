package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.android_testify.compose.parameterized

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.CoffeeDrinkList
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.setContent
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :lazycolumnscreen:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :lazycolumnscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments). Therefore, we need to create 2 different classes to separate @UnhappyPath and
 * @HappyPath tests
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+
 */
@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
            HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var composableScenarioRule = ActivityScenarioForComposableRule(
        config = testItem.item
    )

    @get:Rule(order = 2)
    val screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(exactness = 0.85f)
    )

    @ScreenshotInstrumentation
    @HappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        screenshotRule
            .withScenario(composableScenarioRule.activityScenario)
            .setScreenshotViewProvider {
                composableScenarioRule
                    .setContent {
                        AppTheme {
                            CoffeeDrinkList(coffeeDrink = coffeeDrink)
                        }
                    }.composeView
            }
            .generateDiffs(true)
            .assertSame(
                name = "${testItem.name}_Parameterized"
            )
    }
}

@RunWith(Parameterized::class)
class CoffeeDrinkListComposableParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var composableScenarioRule = ActivityScenarioForComposableRule(
        config = testItem.item
    )

    @get:Rule(order = 2)
    val screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(exactness = 0.85f)
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ComposableTest
    @Test
    fun snapComposable() {
        screenshotRule
            .withScenario(composableScenarioRule.activityScenario)
            .setScreenshotViewProvider {
                composableScenarioRule
                    .setContent {
                        AppTheme {
                            CoffeeDrinkList(coffeeDrink = coffeeDrink)
                        }
                    }.composeView
            }
            .generateDiffs(true)
            .assertSame(
                name = "${testItem.name}_Parameterized"
            )
    }
}
