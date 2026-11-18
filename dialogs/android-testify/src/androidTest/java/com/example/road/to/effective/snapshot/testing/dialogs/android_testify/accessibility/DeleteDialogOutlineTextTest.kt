package com.example.road.to.effective.snapshot.testing.dialogs.android_testify.accessibility

import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.DialogWidth
import com.example.road.to.effective.snapshot.testing.dialogs.android_testify.itemArray
import com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.waitForIdleSync
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineText
import sergio.sastre.uitesting.utils.testrules.accessibility.outlinetext.OutlineTextTestRule
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :dialogs:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 * 2. Verify:
 *    ./gradlew :dialogs:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :dialogs:android-testify:pixel3api32DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :dialogs:android-testify:copyScreenshots -Pdevices=pixel3api32
 *  - Assert
 *    ./gradlew :dialogs:android-testify:pixel3api32DebugAndroidTest -PuseTestStorage -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.AccessibilityTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */
class DeleteDialogOutlineTextTest {

    private val deleteItem = DeleteDialogTestItem(
        bulletTexts = listOf(R.string.largest, R.string.middle, R.string.shortest),
        dialogWidth = DialogWidth.NORMAL,
    )

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val outlineTextTestRule =
        OutlineTextTestRule(OutlineText.ENABLED)

    @get:Rule(order = 2)
    var activityScenarioRule = ActivityScenarioForViewRule(
        config = deleteItem.viewConfig,
        backgroundColor = TRANSPARENT
    )

    @get:Rule(order = 3)
    var screenshotRule = ScreenshotScenarioRule(
        configuration = TestifyConfiguration(
            exactness = 0.85f
        )
    )

    @ScreenshotInstrumentation
    @AccessibilityTest
    @Test
    fun snapDialog() {
        screenshotRule
            .withScenario(activityScenarioRule.activityScenario)
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
            .generateDiffs(true)
            .waitForIdleSync()
            .assertSame(name = "DeleteDialog_OutlineText")
    }
}
