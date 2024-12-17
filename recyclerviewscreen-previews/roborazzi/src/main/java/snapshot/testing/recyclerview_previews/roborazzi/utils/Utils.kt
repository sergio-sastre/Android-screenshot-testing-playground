package snapshot.testing.recyclerview_previews.roborazzi.utils

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R.*
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.TrainAllClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItemPayload
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.addOrRemove

fun generateMemorise(activity: Context, titleSuffix: String = ""): Memorise {
    val memoriseTitle = activity.getString(string.english_memorise_title)
    val titleText = if (titleSuffix.isBlank()) memoriseTitle else "$memoriseTitle $titleSuffix"
    return Memorise(
        id = 2,
        landmark = 4,
        srcLang = Language.English,
        title = titleText,
        text = activity.getString(string.english_memorise_body),
        translations = englishTransl.repeated(3)
    )
}

fun generateMemoriseItem(
    activity: Context,
    rightAligned: Boolean,
    titleSuffix: String = ""
) =
    MemoriseItem(
        memorise = generateMemorise(activity, titleSuffix),
        rightAligned = rightAligned,
        currentTime = 0L
    )

private val englishTransl =
    Translation(
        "hallo",
        setOf(),
        Language.English,
        Language.English
    )

private fun Translation.repeated(times: Int): List<Translation> =
    mutableListOf<Translation>().apply {
        repeat(times) { add(this@repeated) }
    }


/**
 * WARNING:
 * This listener is done only to check the animations in interactionMode
 */
class TrainingPreviewInteractionListener(
    private val oldTrainingItem: MutableState<TrainingItem>,
    val updateCallback: (TrainingItemPayload) -> Unit
) : LanguageFilterClickedListener, TrainAllClickedListener {

    override fun onLanguageFilterClicked(lang: Language) {
        val newTrainingItem = oldTrainingItem.value.copy(
            activeLangs = oldTrainingItem.value.activeLangs.addOrRemove(lang)
        )
        val trainingItemPayload = TrainingItemPayload(
            newTrainingItem = newTrainingItem,
            oldTrainingItem = oldTrainingItem.value
        )
        updateCallback(trainingItemPayload)
        oldTrainingItem.value = newTrainingItem
    }

    override fun onTrainAllClicked() {
        // no-op
    }
}

val initialTrainingItem = TrainingItem(
    trainingByLang = mapOf(
        Language.English to translations(3),
        Language.Russian to translations(5),
        Language.German to translations(1)
    ),
    activeLangs = setOf(Language.Russian, Language.German)
)

val emptyItem = TrainingItem(
    trainingByLang = emptyMap(),
    activeLangs = emptySet()
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

class ThemeProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(
            style.Theme_RoadToEffectiveSnapshotTesting,
            style.Theme_Custom,
        )
}