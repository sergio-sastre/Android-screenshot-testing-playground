package com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary

import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.utils.sdkVersion
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
 *  1. Paparazzi: ./gradlew :dialogs:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :dialogs:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :dialogs:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :dialogs:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :dialogs:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :dialogs:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :dialogs:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :dialogs:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/dialogs/crosslibrary/
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
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
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
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
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
