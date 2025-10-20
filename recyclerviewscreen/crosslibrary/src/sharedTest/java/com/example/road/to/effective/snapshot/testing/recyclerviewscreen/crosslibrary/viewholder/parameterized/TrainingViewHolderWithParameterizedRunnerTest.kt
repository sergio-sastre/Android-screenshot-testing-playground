package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder.parameterized

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized.Parameters
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import sergio.sastre.uitesting.utils.crosslibrary.annotations.CrossLibraryScreenshot
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

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
 *
 * WARNING 1: Running these tests with pseudolocales (i.e. locale = "en-rXA" or locale = "ar-rXB")
 *            throws an exception with Paparazzi ONLY
 *
 * WARNING 2: Paparazzi cannot handle the simultaneous animations properly and renders wrong screenshots
 *            whereas Roborazzi, Shot, Dropshots and Android-Testify do render them correctly
 */
@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class TrainingViewHolderParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
                HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val screenshotRule = defaultCrossLibraryScreenshotTestRule(
        config = testItem.item.viewConfig,
    )

    @CrossLibraryScreenshot
    @Test
    fun snapViewHolder() {
        val layout = screenshotRule.inflate(R.layout.training_row)

        val viewHolder = screenshotRule.waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        screenshotRule.snapshotViewHolder(
            viewHolder = viewHolder,
            name = "${testItem.name}_Parameterized", // testItem names are already long
        )
    }
}

@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class TrainingViewHolderParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val screenshotRule = defaultCrossLibraryScreenshotTestRule(
        config = testItem.item.viewConfig,
    )

    @CrossLibraryScreenshot
    @Test
    fun snapViewHolder() {
        val layout = screenshotRule.inflate(R.layout.training_row)

        val viewHolder = screenshotRule.waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        screenshotRule.snapshotViewHolder(
            viewHolder = viewHolder,
            name = "${testItem.name}_Parameterized", // testItem names are already long
        )
    }
}
