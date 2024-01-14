package com.example.road.to.effective.snapshot.testing.dialogs.android_testify.dialog.parameterized

import android.graphics.Color.*
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.UnhappyPathTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.itemArray
import com.example.road.to.effective.snapshot.testing.testannotations.DialogTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForView
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:android-testify:screenshotRecord
 * 2. Verify:
 *    ./gradlew :dialogs:android-testify:screenshotTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :dialogs:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :dialogs:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage
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
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        activityBackgroundColor = TRANSPARENT,
        exactness = 0.85f,
        config = deleteItem.viewConfig,
    )

    @ScreenshotInstrumentation
    @Test
    // You can also filter tests by custom annotation (not working with Gradle Managed Devices though)
    // For that, pass the annotation to the command. For instance, for DialogTest:
    // -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
    @HappyPath
    @DialogTest
    fun snapDialog() {
        screenshotRule
            .setScreenshotViewProvider {
                val activity = screenshotRule.activity
                val dialog =
                    waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
                        DialogBuilder.buildDeleteDialog(
                            ctx = activity,
                            onDeleteClicked = {},
                            bulletTexts = itemArray(
                                activity,
                                listOf(R.string.largest, R.string.middle, R.string.shortest),
                            )
                        )
                    }
                dialog.show()
                dialog.window!!.decorView
            }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "DeleteDialog_${testItem.name}_Parameterized")
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        activityBackgroundColor = TRANSPARENT,
        exactness = 0.85f,
        config = deleteItem.viewConfig,
    )

    @ScreenshotInstrumentation
    @Test
    // You can also filter tests by custom annotation (not working with Gradle Managed Devices though)
    // For that, pass the annotation to the command. For instance, for DialogTest:
    // -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
    @UnhappyPath
    @DialogTest
    fun snapDialog() {
        screenshotRule
            .setScreenshotViewProvider {
                val activity = screenshotRule.activity
                val dialog =
                    waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
                        DialogBuilder.buildDeleteDialog(
                            ctx = activity,
                            onDeleteClicked = {},
                            bulletTexts = itemArray(
                                activity,
                                listOf(R.string.largest, R.string.middle, R.string.shortest),
                            )
                        )
                    }
                dialog.show()
                dialog.window!!.decorView
            }
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(name = "DeleteDialog_${testItem.name}_Parameterized")
    }
}
