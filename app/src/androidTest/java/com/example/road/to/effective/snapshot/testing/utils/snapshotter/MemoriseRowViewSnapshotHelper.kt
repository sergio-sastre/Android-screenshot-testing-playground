package com.example.road.to.effective.snapshot.testing.utils.snapshotter

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testparameterinjector.memoriseitem.MemoriseTestItem
import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import com.example.road.to.effective.snapshot.testing.utils.objectmother.MemoriseTestItemObjectMother.generateMemoriseItem
import com.karumi.shot.ScreenshotTest
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.inflate
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForView
import java.util.*

object MemoriseRowViewSnapshotHelper : ScreenshotTest {

    fun snapMemoriseViewHolder(testItem: MemoriseTestItem) {
        val configItem = ConfigTestItem(
            orientation = testItem.orientation,
            uiMode = UiMode.DAY,
            locale = "en",
            fontSize = FontSize.NORMAL,
            name = "Memorise_rightAligned_" + testItem.rightAligned + "_" + testItem.orientation.name,
        )
        val activity =
            ActivityScenarioConfigurator.ForView()
                .setFontSize(configItem.fontSize)
                .setLocale(configItem.locale)
                .setUiMode(configItem.uiMode)
                .setInitialOrientation(configItem.orientation)
                .launchConfiguredActivity()
                .waitForActivity()

        val memoriseItem = generateMemoriseItem(activity, testItem.rightAligned)

        val layout = waitForView {
            activity.inflate(R.layout.memorise_row)
        }

        val view = waitForView {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(item = memoriseItem)
            }
        }

        compareScreenshot(
            holder = view,
            heightInPx = layout.height,
            name = configItem.name
        )
    }
}
