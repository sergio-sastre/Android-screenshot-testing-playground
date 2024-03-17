package com.example.road.to.effective.snapshot.testing.dialogs.sharedtests

import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.dialogs.sharedtests.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Roborazzi: ./gradlew :dialogs:shot+roborazzi:recordRoborazziDebug
 *  2. Shot:      ./gradlew :dialogs:shot+roborazzi:executeScreenshotTest -Precord
 *
 * 2. Verify task:
 *  1. Roborazzi: ./gradlew :dialogs:shot+roborazzi:verifyRoborazziDebug
 *  2. Shot:      ./gradlew :dialogs:shot+roborazzi:executeScreenshotTest
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/dialogs/sharedtests/
 *  Therefore, these tests take longer (more tests + downloading of several SDKs)
 */
@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    private val deleteItem
        get() = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
                HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(config = deleteItem.viewConfig)

    @HappyPath
    @Test
    fun snapDialog() {
        val context = screenshotRule.context

        val dialog =
            screenshotRule.waitForMeasuredDialog {
                DialogBuilder.buildDeleteDialog(
                    ctx = context,
                    onDeleteClicked = {},
                    bulletTexts = itemArray(context, deleteItem.bulletTexts)
                )
            }

        screenshotRule.snapshotDialog(
            dialog = dialog,
            name = "DeleteDialog_${testItem.name}_Parameterized_API_$sdkVersion"
        )
    }
}

@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {
    private val deleteItem
        get() = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(config = deleteItem.viewConfig)

    @UnhappyPath
    @Test
    fun snapDialog() {
        val context = screenshotRule.context

        val dialog =
            screenshotRule.waitForMeasuredDialog {
                DialogBuilder.buildDeleteDialog(
                    ctx = context,
                    onDeleteClicked = {},
                    bulletTexts = itemArray(context, deleteItem.bulletTexts)
                )
            }

        screenshotRule.snapshotDialog(
            dialog = dialog,
            name = "DeleteDialog_${testItem.name}_Parameterized_API_$sdkVersion"
        )
    }
}
