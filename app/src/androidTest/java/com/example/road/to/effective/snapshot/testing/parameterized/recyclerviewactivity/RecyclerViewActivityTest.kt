package com.example.road.to.effective.snapshot.testing.parameterized.recyclerviewactivity

import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.RecyclerViewActivityObjectMother
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.RecyclerViewActivitySnapshotHelper
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.fontsize.FontSizeTestRule
import sergio.sastre.uitesting.utils.testrules.locale.LocaleTestRule

@RunWith(Parameterized::class)
class RecyclerViewTestRuleActivity(private val testItem: ConfigTestItem) : ScreenshotTest {

    @get:Rule
    val localeTestRule = LocaleTestRule(testItem.locale)

    @get:Rule
    val fontSizeTestRule = FontSizeTestRule(testItem.fontSize)

    companion object {
        @JvmStatic
        @Parameters
        fun data(): Array<ConfigTestItem> =
            arrayOf(
                RecyclerViewActivityObjectMother.withUiMode(UiMode.NIGHT),
                RecyclerViewActivityObjectMother.withUiMode(UiMode.DAY)
            )
    }

    @HappyPath
    @Test
    fun snapRecyclerViewActivity() {
        RecyclerViewActivitySnapshotHelper.snapRecyclerViewActivity(testItem)
    }
}