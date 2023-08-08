package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder.parameterized

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.utils.defaultCrossLibraryScreenshotTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized.Parameters
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import sergio.sastre.uitesting.utils.crosslibrary.runners.ParameterizedCrossLibraryScreenshotTestRunner

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
 *  src/test/resources/com/example/road/to/effective/snapshot/testing/recyclerviewscreen/crosslibrary/
 */
@RunWith(ParameterizedCrossLibraryScreenshotTestRunner::class)
class TrainingViewHolderParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val screenshotRule = defaultCrossLibraryScreenshotTestRule(
        config = testItem.item.viewConfig,
    )

    @HappyPath
    @ViewHolderTest
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
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val screenshotRule = defaultCrossLibraryScreenshotTestRule(
        config = testItem.item.viewConfig,
    )

    @UnhappyPath
    @ViewHolderTest
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
