package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder

import android.os.Build
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :recyclerviewscreen:crosslibrary:recordPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :recyclerviewscreen:crosslibrary:recordRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :recyclerviewscreen:crosslibrary:executeScreenshotTest -Precord -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :recyclerviewscreen:crosslibrary:connectedAndroidTest -Pdropshots.record -PscreenshotLibrary=dropshots
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :recyclerviewscreen:crosslibrary:verifyPaparazziDebug -PscreenshotLibrary=paparazzi
 *  2. Roborazzi: ./gradlew :recyclerviewscreen:crosslibrary:verifyRoborazziDebug -PscreenshotLibrary=roborazzi
 *  3. Shot:      ./gradlew :recyclerviewscreen:crosslibrary:executeScreenshotTest -PscreenshotLibrary=shot
 *  4. Dropshots: ./gradlew :recyclerviewscreen:crosslibrary:connectedAndroidTest -PscreenshotLibrary=dropshots
 *
 *  NOTE: These tests run on different api levels when executed with Roborazzi.
 *  Those api levels are defined in the robolectric.properties file under
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/recyclerviewscreen/crosslibrary/viewholder/
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class MemoriseViewHolderHappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfigForView(theme = "Theme.RoadToEffectiveSnapshotTesting")
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

/**
 * Example with ActivityScenarioConfigurator.ForView() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForViewRule()
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class MemoriseViewHolderUnhappyPathTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(
            config = ScreenshotConfigForView(
                theme = "Theme.RoadToEffectiveSnapshotTesting",
                orientation = Orientation.LANDSCAPE,
                locale = "en",
                uiMode = UiMode.NIGHT,
                displaySize = DisplaySize.LARGEST,
                fontSize = FontSize.HUGE,
            )
        )

    @UnhappyPath
    @ViewHolderTest
    @Test
    fun snapMemoriseViewHolderUnhappyPath() {
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
                    ))
            }
        }

        screenshotRule.snapshotViewHolder(
            viewHolder = viewHolder,
            name = "MemoriseView_Unhappy_API_$sdkVersion"
        )
    }
}
