package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder

import android.os.Build
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :recyclerviewscreen:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :recyclerviewscreen:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :recyclerviewscreen:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :recyclerviewscreen:crosslibrary:recordScreenshots -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :recyclerviewscreen:crosslibrary:screenshotRecord -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *                ./gradlew :recyclerviewscreen:crosslibrary:pixel3api30DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage -PrecordModeGmd
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :recyclerviewscreen:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :recyclerviewscreen:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :recyclerviewscreen:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :recyclerviewscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *  5. Testify:   ./gradlew :recyclerviewscreen:crosslibrary:screenshotTest -PscreenshotLibrary=android-testify
 *     Testify via gradle manages devices (copy recorded screenshots + assert):
 *          - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *               ./gradlew :recyclerviewscreen:crosslibrary:copyScreenshots -Pdevices=pixel3api30
 *          - Assert
 *               ./gradlew :recyclerviewscreen:crosslibrary:pixel3api30DebugAndroidTest -PscreenshotLibrary=android-testify -PuseTestStorage
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/recyclerviewscreen/crosslibrary/viewholder/parameterized
 *  Therefore, these tests take longer (more tests + downloading of several SDKs)
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class MemoriseViewHolderHappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfigForView(
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            )
        )

    @CrossLibraryScreenshot
    @Test
    fun snapMemoriseViewHolderHappyPath() {
        val sdkVersion = Build.VERSION.SDK_INT
        val layout = screenshotRule.inflate(R.layout.memorise_row)

        val viewHolder = screenshotRule.waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(
                    generateMemoriseItem(
                        rightAligned = false,
                        context = screenshotRule.context,
                        titleSuffix = "API $sdkVersion"
                    )
                )
            }
        }

        screenshotRule.snapshotViewHolder(
            viewHolder = viewHolder,
            name = "MemoriseView_Happy_API_$sdkVersion"
        )
    }
}
