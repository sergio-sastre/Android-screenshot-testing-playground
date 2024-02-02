package com.example.road.to.effective.snapshot.testing.dialogs.android_testify.dialog.parameterized

import android.graphics.Color.*
import androidx.test.filters.SdkSuppress
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.UnhappyPathTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.itemArray
import com.example.road.to.effective.snapshot.testing.testannotations.DialogTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
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
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :dialogs:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :dialogs:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        activityBackgroundColor = TRANSPARENT,
        exactness = 0.85f,
        config = deleteItem.viewConfig,
    )

    @ScreenshotInstrumentation
    @HappyPath
    @DialogTest
    @Test
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
            .assertSame(name = "DeleteDialog_${testItem.name}_TestParameter")
    }
}

@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        activityBackgroundColor = TRANSPARENT,
        exactness = 0.85f,
        config = deleteItem.viewConfig,
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @DialogTest
    @Test
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
            .assertSame(name = "DeleteDialog_${testItem.name}_TestParameter")
    }
}
