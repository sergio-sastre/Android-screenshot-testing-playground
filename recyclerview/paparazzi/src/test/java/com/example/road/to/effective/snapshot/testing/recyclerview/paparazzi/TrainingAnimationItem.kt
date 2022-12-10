package com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi

import androidx.constraintlayout.widget.ConstraintLayout
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingItemPayload
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingViewHolder
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class TrainingAnimationItem {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig =
        DeviceConfig.PIXEL_5.copy(
            softButtons = false,
            screenHeight = 1,
        ),
        supportsRtl = true,
        theme = "Theme.RoadToEffectiveSnapshotTesting",
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        environment = detectEnvironment().copy(
            platformDir = "${androidHome()}/platforms/android-32",
            compileSdkVersion = 32
        ),
    )

    @Test
    fun snapTrainingItem() {
        val layout = paparazzi.inflate<ConstraintLayout>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = wordsInSomeLangsTrainingItem, languageClickedListener = null)
            }

        Executors.newSingleThreadScheduledExecutor().schedule({
            val trainingItemPayload = TrainingItemPayload(
                oldTrainingItem = wordsInSomeLangsTrainingItem,
                newTrainingItem = TrainingItem(emptyMap(), emptySet()),
            )
            viewHolder.update(trainingItemPayload)
            //layout.findViewById<DigitTextView>(R.id.amountText).setValue(0)
        }, 500, TimeUnit.MILLISECONDS)

        paparazzi.gif(
            view = viewHolder.itemView,
            start = 250,
            end = 2_000,
            fps = 30,
            name = "Animate_to_empty_state",
        )

        /*
        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 3_000,
            name = "TrainingViewHolderAnimation",
        )

         */
    }
}

private val wordsInSomeLangsTrainingItem = TrainingItem(
    trainingByLang = mapOf(
        Language.English to translations(3),
        Language.Russian to translations(5),
        Language.German to translations(1)
    ),
    activeLangs = setOf(Language.Russian, Language.German)
)

private fun translations(amount: Int): List<Translation> {
    val translation = translation()
    return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
}

private fun translation(): Translation =
    Translation("word", setOf(IntRange(0, 4)), Language.English, Language.English)