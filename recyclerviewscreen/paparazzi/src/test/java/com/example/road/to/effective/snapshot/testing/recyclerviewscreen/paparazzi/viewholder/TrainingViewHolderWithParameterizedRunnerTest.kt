package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.viewholder

import android.view.View
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder

/**
 * Execute the command below to run only these tests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:paparazzi:recordPaparazziDebug --tests 'TrainingViewHolderTest'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:paparazzi:verifyPaparazziDebug --tests 'TrainingViewHolderTest'
 *
 * WARNING: Running these tests with pseudolocales (i.e. locale = "en-rXA" or locale = "ar-rXB")
 *          throws an exception, but works perfectly with non-pseudolocales (e.g. locale = "en")
 */
class TrainingViewHolderTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5.copy(
            screenHeight = 1,
            locale = "en-rXA"
        ),
        supportsRtl = true, // needed for "ar" locale
        theme = "Theme.RoadToEffectiveSnapshotTesting",
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
    )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.training_row)

        val viewHolder =
            TrainingViewHolder(layout).apply {
                bind(item = wordsInSomeLangsTrainingItem, languageClickedListener = null)
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            offsetMillis = 3_000,
            name = "Pseudolocale_Crash",
        )
    }
}

val wordsInSomeLangsTrainingItem = TrainingItem(
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
    Translation(
        "word",
        setOf(IntRange(0, 4)),
        Language.English,
        Language.English
    )
