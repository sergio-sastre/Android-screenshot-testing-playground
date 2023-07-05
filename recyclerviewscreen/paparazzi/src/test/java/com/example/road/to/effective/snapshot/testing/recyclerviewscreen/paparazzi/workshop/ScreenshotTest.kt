package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.workshop

/**
 * Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug
 * Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug
 *
 * Execute tests in this package only by adding the following to the previous command
 * --tests '*ScreenshotTest'
 *
 * Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests '*ScreenshotTest'
 * Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests '*ScreenshotTest'
 */
import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.InstantAnimationsRule
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.DisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setDisplaySize
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.emptyTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder.parameterized.wordsInSomeLangsTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import org.junit.Rule
import org.junit.Test

class ViewHolderScreenshotTest {
    @get:Rule
    val instantAnimationsRule = InstantAnimationsRule()

    @get:Rule
    val paparazzi =
        Paparazzi(
            showSystemUi = false,
            deviceConfig = DeviceConfig.PIXEL_XL.copy(
                softButtons = false,
                nightMode = NightMode.NIGHT,
                fontScale = 1.3f,
                locale = "ar",
            ),
            supportsRtl = true, // needed for "ar" locale
            theme = "Theme_Custom",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)
        val viewHolder = TrainingViewHolder(layout).apply {
            bind(
                item = wordsInSomeLangsTrainingItem,
                languageClickedListener = null,
            )
        }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            name = "trainingVH",
            offsetMillis = 8_000,
        )
    }
}