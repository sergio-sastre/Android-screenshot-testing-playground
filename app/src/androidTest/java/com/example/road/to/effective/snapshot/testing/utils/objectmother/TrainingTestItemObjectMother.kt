package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.parameterized.trainingitem.TrainingTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.values
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TranslationsObjectMother.translationsPerLang
import sergio.sastre.fontsize.FontScale

object TrainingTestItemObjectMother {

    fun withoutWordsToTrainHappyPath() =
        TrainingTestItem(
            FontScale.NORMAL,
            ViewWidth.DEVICE_WIDTH,
            TrainingItem(
                trainingByLang = mapOf(),
                activeLangs = emptySet()
            ),
            "WITHOUT_WORDS_TO_TRAIN_HAPPY_PATH"
        )

    fun withWordsToTrainHappyPath(trainingItem: TrainingItem) =
        TrainingTestItem(
            FontScale.NORMAL,
            ViewWidth.DEVICE_WIDTH,
            trainingItem,
            "WITH_WORDS_TO_TRAIN_HAPPY_PATH"
        )

    fun withoutWordsToTrainUnhappyPath(viewWidth: ViewWidth) =
        TrainingTestItem(
            FontScale.HUGE,
            viewWidth,
            TrainingItem(
                trainingByLang = mapOf(),
                activeLangs = emptySet()
            ),
            "WITHOUT_WORDS_TO_TRAIN_UNHAPPY_PATH_${viewWidth.name}"
        )

    fun withWordsToTrainUnhappyPath() =
        TrainingTestItem(
            FontScale.HUGE,
            ViewWidth.NARROW,
            TrainingItem(
                trainingByLang = translationsPerLang(999_999),
                activeLangs = values().toSet()
            ),
            "WITH_WORDS_TO_TRAIN_UNHAPPY_PATH"
        )
}