package com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi.parameterized

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi.setOrientation
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingViewHolder
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 *
 * On the other hand, TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@RunWith(TestParameterInjector::class)
class TrainingItemHappyPath(
    @TestParameter val testItem: HappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            softButtons = false,
            nightMode = deviceConfig.nightMode,
            locale = deviceConfig.locale,
            fontScale = deviceConfig.fontScale,
        ).setOrientation(deviceConfig.orientation),
        theme = deviceConfig.theme,
        supportsRtl = true,
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        environment = detectEnvironment().copy(
            platformDir = "${androidHome()}/platforms/android-32",
            compileSdkVersion = 32
        ),
    )

    @Test
    fun snapTrainingItem() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 1_500,
            name = "${testItem.name}_TestParameter",
        )
    }
}

@RunWith(TestParameterInjector::class)
class TrainingItemUnhappyPath(
    @TestParameter val testItem: UnhappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            softButtons = false,
            nightMode = deviceConfig.nightMode,
            locale = deviceConfig.locale,
            fontScale = deviceConfig.fontScale,
        ).setOrientation(deviceConfig.orientation),
        supportsRtl = true,
        theme = deviceConfig.theme,
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        environment = detectEnvironment().copy(
            platformDir = "${androidHome()}/platforms/android-32",
            compileSdkVersion = 32
        ),
    )

    @Test
    fun snapTrainingItem() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 1_500,
            name = "${testItem.name}_TestParameter",
        )
    }
}
