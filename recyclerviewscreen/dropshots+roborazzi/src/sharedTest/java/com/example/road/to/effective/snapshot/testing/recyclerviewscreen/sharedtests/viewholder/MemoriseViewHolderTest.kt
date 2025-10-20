package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.sharedtests.viewholder

import android.os.Build
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.sharedtests.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Roborazzi: ./gradlew :recyclerviewscreen:dropshots+roborazzi:recordRoborazziDebug
 *  4. Dropshots: ./gradlew :recyclerviewscreen:dropshots+roborazzi:recordScreenshots
 *
 * 2. Verify task:
 *  2. Roborazzi: ./gradlew :recyclerviewscreen:dropshots+roborazzi:verifyRoborazziDebug
 *  4. Dropshots: ./gradlew :recyclerviewscreen:dropshots+roborazzi:connectedAndroidTest
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/recyclerviewscreen/sharedtests/viewholder/
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

    @HappyPath
    @ViewHolderTest
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
