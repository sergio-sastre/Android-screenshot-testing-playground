package com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.memorisetext.MemoriseViewHolder
import org.junit.Rule
import org.junit.Test

/**
 * Example with ActivityScenarioForViewRule
 */
class MemoriseViewHolderHappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                locale = "en",
            ),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
            environment = detectEnvironment().copy(
                platformDir = "${androidHome()}/platforms/android-32",
                compileSdkVersion = 32
            ),
        )

    // TODO - @HappyPath
    @Test
    fun snapViewHolder() {
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
 * Example with ActivityScenarioConfigurator.ForView()
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
            ).landscapeOrientation(),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
            environment = detectEnvironment().copy(
                platformDir = "${androidHome()}/platforms/android-32",
                compileSdkVersion = 32
            ),
        )

    // TODO @UnhappyPath
    @Test
    fun snapViewHolder() {
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
