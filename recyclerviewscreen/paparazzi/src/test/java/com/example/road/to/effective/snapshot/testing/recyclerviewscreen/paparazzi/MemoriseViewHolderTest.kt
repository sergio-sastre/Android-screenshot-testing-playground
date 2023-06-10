package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.DisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*ViewHolder*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*ViewHolder*'
 */

/**
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapViewHolder() {
        // Must be called inside the test: paparazzi.context is null before
        paparazzi.setDisplaySize(DisplaySize.NORMAL)

        val layout = paparazzi.inflate<View>(R.layout.memorise_row)
        val view = MemoriseViewHolder(
            container = layout,
            itemEventListener = null,
            animationDelay = 0L
        ).apply {
            bind(generateMemoriseItem(rightAligned = false, activity = paparazzi.context))
        }

        paparazzi.snapshot(
            view = view.itemView,
            name = "MemoriseView_Happy"
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForView() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForViewRule()
 */
class MemoriseViewHolderUnhappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig =
            DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                nightMode = NightMode.NIGHT,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapViewHolder() {
        // Must be called inside the test: paparazzi.context is null before
        paparazzi.setDisplaySize(DisplaySize.LARGEST)

        val layout = paparazzi.inflate<View>(R.layout.memorise_row)

        val viewHolder =
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = true, activity = paparazzi.context))
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            name = "MemoriseView_Unhappy"
        )
    }
}
