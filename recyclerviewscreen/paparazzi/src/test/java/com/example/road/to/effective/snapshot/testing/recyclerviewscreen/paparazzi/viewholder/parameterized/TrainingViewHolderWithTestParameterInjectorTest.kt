package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*ViewHolder*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*ViewHolder*'
 *
 * WARNING: Running these tests with pseudolocales (i.e. locale = "en-rXA" or locale = "ar-rXB")
 *          throws an exception
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 */
@RunWith(TestParameterInjector::class)
class TrainingViewHolderTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            nightMode = deviceConfig.nightMode,
            locale = deviceConfig.locale,
            fontScale = deviceConfig.fontScale,
        ).setPhoneOrientation(deviceConfig.orientation),
        theme = deviceConfig.theme,
        supportsRtl = true, // needed for "ar" locale
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 1_500,
            name = "${testItem.name}_TestParam",
        )
    }
}

@RunWith(TestParameterInjector::class)
class TrainingViewHolderTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            nightMode = deviceConfig.nightMode,
            locale = deviceConfig.locale,
            fontScale = deviceConfig.fontScale,
        ).setPhoneOrientation(deviceConfig.orientation),
        supportsRtl = true, // needed for "ar" locale
        theme = deviceConfig.theme,
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 1_500,
            name = "${testItem.name}_TestParam",
        )
    }
}
