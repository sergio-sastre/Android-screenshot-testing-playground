package com.example.road.to.effective.snapshot.testing.parameterized

import androidx.annotation.StringRes
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.road.to.effective.snapshot.testing.DialogBuilder
import com.example.road.to.effective.snapshot.testing.MainActivity
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme.MATERIAL_DARK_DIALOG
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme.MATERIAL_LIGHT_DIALOG
import com.example.road.to.effective.snapshot.testing.utils.SmokeTest
import com.example.road.to.effective.snapshot.testing.utils.waitForActivity
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.FontScale.*
import sergio.sastre.fontsize.FontSizeActivityScenario
import sergio.sastre.fontsize.testrule.FontScaleRules

/**
 * In this file you will find the following examples:
 * 1. How to write a Snapshot test with [FontSizeActivityScenario] (See [AllDeleteDialogSnapshotTest])
 * Also take a look at [ScreenshotTest.snapDeleteDialogWithActivityScenario]
 *
 * 2. How to write a snapshot test with [FontScaleRules] (See [BasicDeleteDialogSnapshotTest])
 * Also take a look at [ScreenshotTest.snapDeleteDialogWithTestRule]
 */
@RunWith(Parameterized::class)
class AllDeleteDialogSnapshotTest(private val testItem: TestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TestItem> =
            arrayOf(
                TestItem(
                    SMALL,
                    MATERIAL_DARK_DIALOG,
                    arrayOf(R.string.shortest),
                    "DARK_SMALL"
                ),
                TestItem(
                    HUGE,
                    MATERIAL_DARK_DIALOG,
                    repeatedItem(7, R.string.largest),
                    "DARK_HUGE"
                )
            )
    }

    @Test
    fun snapDialog() {
        snapDeleteDialogWithActivityScenario(testItem)
    }
}

/**
 * This tests showcase how to use FontScaleTestRule
 */
@RunWith(Parameterized::class)
class BasicDeleteDialogSnapshotTest(private val testItem: TestItem) : ScreenshotTest {

    @get:Rule
    val fontSize = FontScaleRules.fontScaleTestRule(testItem.fontScale)

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TestItem> = arrayOf(
            TestItem(
                NORMAL,
                MATERIAL_LIGHT_DIALOG,
                arrayOf(R.string.largest, R.string.middle, R.string.shortest),
                "SMOKE"
            )
        )
    }

    @SmokeTest
    @Test
    fun snapDialog() {
        snapDeleteDialogWithTestRule(testItem)
    }
}

class TestItem(
    val fontScale: FontScale,
    val theme: DialogTheme,
    val texts: Array<Int>,
    val testName: String
)

/**
 * The FontSizeTestRule is applied inside the test class and not in the method
 */
private fun ScreenshotTest.snapDeleteDialogWithTestRule(testItem: TestItem) {
    val activity = launch(MainActivity::class.java)
        .waitForActivity(testItem.theme.themId)

    val dialog = waitForView {
        DialogBuilder.buildDeleteDialog(
            activity,
            onDeleteClicked = {/* no-op*/ },
            bulletTexts = itemArray(testItem.texts)
        )
    }

    compareScreenshot(dialog, name = testItem.testName, widthInPx = 800)
}

/**
 * [FontSizeActivityScenario] requires that you add the corresponding activities to the debug/manifest, as
 * stated in the doc [FontSizeTestRule](https://github.com/sergio-sastre/FontSizeTestRule/blob/v1.1.0-SNAPSHOT/README.md)
 */
private fun ScreenshotTest.snapDeleteDialogWithActivityScenario(testItem: TestItem) {
    val activity = FontSizeActivityScenario.launchWith(testItem.fontScale)
        .waitForActivity(testItem.theme.themId)

    val dialog = waitForView {
        DialogBuilder.buildDeleteDialog(
            activity,
            onDeleteClicked = {/* no-op*/ },
            bulletTexts = itemArray(testItem.texts)
        )
    }

    compareScreenshot(dialog, name = testItem.testName, widthInPx = 800)
}

private fun itemArray(@StringRes resources: Array<Int>): Array<String> =
    resources.map { getInstrumentation().targetContext.getString(it) }
        .toTypedArray()

private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): Array<Int> =
    emptyList<Int>().toMutableList().apply {
        repeat(timesRepeated) { add(resource) }
    }.toTypedArray()