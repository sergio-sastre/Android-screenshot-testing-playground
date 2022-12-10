package com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi.parameterized

import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.paparazzi.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingItem

data class DeviceConfig(
    val nightMode: NightMode = NightMode.NOTNIGHT,
    val fontScale: Float = 1.0f,
    val theme: String = "Theme.RoadToEffectiveSnapshotTesting",
    val locale: String = "en",
    val orientation: PhoneOrientation = PhoneOrientation.PORTRAIT
)

data class TrainingTestItem(
    val deviceConfig: DeviceConfig,
    val trainingItem: TrainingItem = TrainingItem(
        trainingByLang = mapOf(),
        activeLangs = emptySet()
    )
)

private fun translationsPerLang(count: Int) =
    Language.values().associateWith { translations(count) }

private fun translations(amount: Int): List<Translation> {
    val translation = translation()
    return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
}

private fun translation(): Translation =
    Translation("word", setOf(IntRange(0, 4)), Language.English, Language.English)

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
            deviceConfig = DeviceConfig(
                locale = "en",
            ),
        ),
    ),
    HAPPY_EN_WITH_WORDS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "en",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
}

enum class UnhappyPathTestItem(val item: TrainingTestItem) {
    UNHAPPY_AR_WITH_WORDS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "ar",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_CUSTOM_THEME_DAY_SR_LATIN_WITH_WORDS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "b+sr+Latn",
                theme = "Theme_Custom",
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_CUSTOM_THEME_NIGHT_SR_CYRYL_WITH_WORDS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "b+sr+Cyrl",
                theme = "Theme_Custom",
                nightMode = NightMode.NIGHT,
            ),
            trainingItem = wordsInSomeLangsTrainingItem,
        ),
    ),
    UNHAPPY_EN_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "en",
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
    UNHAPPY_EN_HUGE_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "en",
                fontScale = 1.3f,
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
    UNHAPPY_EN_NIGHT_LANDSCAPE_WITH_1M_WORDS_IN_ALL_LANGS(
        TrainingTestItem(
            deviceConfig = DeviceConfig(
                locale = "en",
                orientation = PhoneOrientation.LANDSCAPE,
                nightMode = NightMode.NIGHT,
            ),
            trainingItem = oneMillionWordsTrainingItem,
        ),
    ),
}

