package com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary

import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.UnhappyPathTestItem.HUGE_NIGHT
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.utils.sdkVersion
import com.example.road.to.effective.snapshot.testing.dialogs.crosslibrary.utils.userTestFilePath
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.mapper.roborazzi.RoborazziConfig
import sergio.sastre.uitesting.mapper.roborazzi.wrapper.screen.DeviceScreen
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.Canvas
import sergio.sastre.uitesting.utils.crosslibrary.config.BitmapCaptureMethod.PixelCopy
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :dialogs:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :dialogs:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :dialogs:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :dialogs:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :dialogs:crosslibrary:screenshotRecord -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *                ./gradlew :dialogs:crosslibrary:pixel3api32DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage -PrecordModeGmd
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :dialogs:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :dialogs:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :dialogs:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :dialogs:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :dialogs:crosslibrary:screenshotTest -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (copy recorded screenshots + assert):
 *          - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *               ./gradlew :dialogs:crosslibrary:copyScreenshots -Pdevices=pixel3api32
 *          - Assert
 *               ./gradlew :dialogs:crosslibrary:pixel3api32DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/dialogs/crosslibrary/
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
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(config = deleteItem.viewConfig)

    @CrossLibraryScreenshot
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
            .configure(
                RoborazziConfig(
                    deviceScreen = DeviceScreen.Phone.NEXUS_4,
                    filePath = userTestFilePath(),
                    // PixelCopy would distort very large views to make them fit in the screen window
                    // Therefore use Canvas for HUGE_NIGHT
                    bitmapCaptureMethod = when(testItem == HUGE_NIGHT) {
                        true -> Canvas()
                        false -> PixelCopy()
                    }
                )
            )

    @CrossLibraryScreenshot
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
