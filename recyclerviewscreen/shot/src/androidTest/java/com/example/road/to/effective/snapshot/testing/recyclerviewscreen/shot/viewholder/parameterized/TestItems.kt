package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.shot.viewholder.parameterized

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

data class TrainingTestItem(
    val viewConfig: ViewConfigItem,
    val trainingItem: TrainingItem = TrainingItem(
        trainingByLang = mapOf(),
        activeLangs = emptySet()
    )
)

private fun translationsPerLang(count: Int) =
    Language.values().map { lang -> lang to translations(count) }.toMap()

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

private val oneMillionWordsTrainingItem = TrainingItem(
    trainingByLang = translationsPerLang(999_999),
    activeLangs = Language.values().toSet()
)

private val wordsInSomeLangsTrainingItem = TrainingItem(
    trainingByLang = mapOf(
        Language.English to translations(3),
        Language.Russian to translations(5),
        Language.German to translations(1)
    ),
    activeLangs = setOf(Language.Russian, Language.German)
)

enum class HappyPathTestItem(val item: TrainingTestItem) {
    HAPPY_EN_WITHOUT_WORDS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "en",
            ),
        ),
    ),
    HAPPY_EN_WITH_WORDS_IN_SOME_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "en",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: TrainingTestItem) {
    UNHAPPY_AR_WITH_WORDS_IN_SOME_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "ar",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_CUSTOM_THEME_DAY_SR_LATIN_WITH_WORDS_IN_SOME_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "sr-Latn-RS",
                theme = R.style.Theme_Custom,
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_CUSTOM_THEME_NIGHT_SR_CYRYL_CUSTOM_WITH_WORDS_IN_SOME_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "sr-Cyrl-RS",
                theme = R.style.Theme_Custom,
                uiMode = UiMode.NIGHT,
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_EN_XA_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "en_XA",
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
    UNHAPPY_EN_HUGE_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "en",
                fontSize = FontSize.HUGE,
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
    UNHAPPY_EN_NIGHT_LANDSCAPE_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            viewConfig = ViewConfigItem(
                locale = "en",
                orientation = Orientation.LANDSCAPE,
                uiMode = UiMode.NIGHT,
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
}
