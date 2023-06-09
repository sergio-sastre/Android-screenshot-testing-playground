package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.accessibility

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.MemoriseTestItemGenerator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.HappyPathTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.TrainingTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*Accessibility*'
 */
class AccessibilityTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            // For Accessibility, better to use devices in landscape with Paparazzi.
            //
            // As side effect, this will screenshot a landscape layout. This might differ from the
            // portrait one, as it is the case with R.layout.memorise_row
            deviceConfig = DeviceConfig.NEXUS_5_LAND.copy(softButtons = false),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderExtensions = setOf(AccessibilityRenderExtension())
        )

    @Test
    fun snapMemoriseViewHolderWithAccessibility() {
        val layout = paparazzi.inflate<View>(R.layout.memorise_row)
        val view = MemoriseViewHolder(
            container = layout,
            itemEventListener = null,
            animationDelay = 0L
        ).apply {
            bind(
                MemoriseTestItemGenerator.generateMemoriseItem(
                    rightAligned = false,
                    activity = paparazzi.context
                )
            )
        }

        paparazzi.snapshot(
            view = view.itemView,
            name = "MemoriseView_Accessibility"
        )
    }

    @Test
    fun snapTrainingViewHolderWithAccessibility() {
        val trainingTestItem = HappyPathTestItem.HAPPY_EN_WITH_WORDS.item

        val layout = paparazzi.inflate<View>(R.layout.training_row)
        val trainingItem = TrainingTestItem(
            deviceConfig = trainingTestItem.deviceConfig,
            trainingItem = trainingTestItem.trainingItem,
        )
        val view =
            TrainingViewHolder(layout).apply {
                bind(item = trainingItem.trainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = view.itemView,
            offsetMillis = 3_000L,
            name = "TrainingView_Accessibility"
        )
    }
}
