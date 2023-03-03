package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.parameterized

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+, throwing
 * java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder error in lower APIs
 */
@RunWith(Parameterized::class)
class TrainingViewHolderParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            softButtons = false,
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
            offsetMillis = 3_000,
            name = "${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class TrainingViewHolderParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) {
    private val deviceConfig
        get() = testItem.item.deviceConfig

    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            softButtons = false,
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
            offsetMillis = 3_000,
            name = "${testItem.name}_Parameterized",
        )
    }
}



