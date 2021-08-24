package com.example.road.to.effective.snapshot.testing.parameterized

import androidx.annotation.StringRes
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.road.to.effective.snapshot.testing.*
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme.MATERIAL_DARK
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme.MATERIAL_LIGHT
import com.example.road.to.effective.snapshot.testing.utils.SmokeTest
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.rules.ForceLocaleRule
import com.example.road.to.effective.snapshot.testing.utils.waitForActivity
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.FontScale.*
import sergio.sastre.fontsize.FontScaleRules
import java.util.*

@RunWith(Parameterized::class)
class AllDeleteDialogSnapshotTest(private val testItem: TestItem) : ScreenshotTest {

    private val fontSize = FontScaleRules.fontScaleTestRule(testItem.fontScale)
    private val languageRule = ForceLocaleRule(testItem.locale)

    @get:Rule
    val rule : RuleChain = RuleChain.outerRule(languageRule).around(fontSize)

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TestItem> =
            arrayOf(
                TestItem(
                    SMALL,
                    Locale("en", "XA"),
                    MATERIAL_DARK,
                    arrayOf(R.string.shortest),
                    "DARK_SMALL"
                ),
                TestItem(
                    HUGE,
                    Locale("en", "XA"),
                    MATERIAL_DARK,
                    repeatedItem(7, R.string.largest),
                    "DARK_HUGE"
                )
            )
    }

    @Test
    fun snapDialog() {
        snapDeleteDialog(testItem)
    }
}

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
                Locale("en"),
                MATERIAL_LIGHT,
                arrayOf(R.string.largest, R.string.middle, R.string.shortest),
                "SMOKE"
            )
        )
    }

    @SmokeTest
    @Test
    fun snapDialog() {
        snapDeleteDialog(testItem)
    }
}

class TestItem(
    val fontScale: FontScale,
    val locale: Locale,
    val theme: DialogTheme,
    val texts: Array<Int>,
    val testName: String
)

private fun ScreenshotTest.snapDeleteDialog(testItem: TestItem) {
    val activity = launch(MainActivity::class.java)
        .waitForActivity(testItem.theme.themId)

    val dialog = waitForView {
        DialogBuilder.buildDeleteDialog(
            activity,
            onDeleteClicked = {/* no-op*/ },
            bulletTexts = itemArray(testItem.texts)
        )
    }

    compareScreenshot(dialog, name = testItem.testName, heightInPx = 1400, widthInPx = 800)
}

private fun itemArray(@StringRes resources: Array<Int>): Array<String> =
    resources.map { getInstrumentation().targetContext.getString(it) }
        .toTypedArray()

private fun repeatedItem(timesRepeated: Int, @StringRes resource: Int): Array<Int> =
    emptyList<Int>().toMutableList().apply {
        repeat(timesRepeated) { add(resource) }
    }.toTypedArray()