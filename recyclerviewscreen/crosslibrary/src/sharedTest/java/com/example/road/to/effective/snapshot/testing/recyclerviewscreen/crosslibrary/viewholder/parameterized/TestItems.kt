package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.crosslibrary.viewholder.parameterized

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForView

data class TrainingTestItem(
    val viewConfig: ScreenshotConfigForView,
    val trainingItem: TrainingItem = TrainingItem(
        trainingByLang = mapOf(),
        activeLangs = emptySet()
    )
)

private fun translationsPerLang(count: Int): Map<Language, List<Translation>> =
    Language.values().associateWith { translations(count) }

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
    WITHOUT_WORDS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "en",
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
        ),
    ),
    WITH_WORDS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "en",
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: TrainingTestItem) {
    AR_WITH_WORDS_IN_SOME_LANGS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "ar",
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    CUSTOM_THEME_DAY_SR_LATIN_WITH_WORDS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "sr-Latn-RS",
                theme = "Theme.Custom",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    CUSTOM_THEME_NIGHT_SR_CYRYL_CUSTOM_WITH_WORDS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "sr-Cyrl-RS",
                theme = "Theme.Custom",
                uiMode = UiMode.NIGHT,
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    HUGE_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "en",
                fontSize = FontSize.HUGE,
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
    NIGHT_LANDSCAPE_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            viewConfig = ScreenshotConfigForView(
                locale = "en",
                orientation = Orientation.LANDSCAPE,
                uiMode = UiMode.NIGHT,
                theme = "Theme.RoadToEffectiveSnapshotTesting",
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
}
