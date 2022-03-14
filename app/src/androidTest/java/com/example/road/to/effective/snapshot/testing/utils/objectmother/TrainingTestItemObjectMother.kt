package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.parameterized.trainingitem.TrainingTestItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.values
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TranslationsObjectMother.translationsPerLang
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

object TrainingTestItemObjectMother {

    fun withoutWordsToTrainHappyPath() =
        TrainingTestItem(
            locale = "en",
            fontScale = FontSize.NORMAL,
            viewWidth = ViewWidth.DEVICE_WIDTH,
            uiMode = UiMode.DAY,
            trainingItem = TrainingItem(
                trainingByLang = mapOf(),
                activeLangs = emptySet()
            ),
            testName = "WITHOUT_WORDS_TO_TRAIN_HAPPY_PATH"
        )

    fun withWordsToTrainHappyPath(trainingItem: TrainingItem) =
        TrainingTestItem(
            locale = "en",
            fontScale = FontSize.NORMAL,
            viewWidth = ViewWidth.DEVICE_WIDTH,
            uiMode = UiMode.DAY,
            trainingItem = trainingItem,
            testName = "WITH_WORDS_TO_TRAIN_HAPPY_PATH"
        )

    fun withoutWordsToTrainUnhappyPath(viewWidth: ViewWidth) =
        TrainingTestItem(
            locale = "en_XA",
            fontScale = FontSize.HUGE,
            viewWidth = viewWidth,
            uiMode = UiMode.NIGHT,
            trainingItem = TrainingItem(
                trainingByLang = mapOf(),
                activeLangs = emptySet()
            ),
            testName = "WITHOUT_WORDS_TO_TRAIN_UNHAPPY_PATH_${viewWidth.name}"
        )

    fun withWordsToTrainUnhappyPath() =
        TrainingTestItem(
            locale = "ar_XB",
            fontScale = FontSize.HUGE,
            viewWidth = ViewWidth.DEVICE_WIDTH,
            uiMode = UiMode.NIGHT,
            trainingItem = TrainingItem(
                trainingByLang = translationsPerLang(999_999),
                activeLangs = values().toSet()
            ),
            testName = "WITH_WORDS_TO_TRAIN_UNHAPPY_PATH"
        )
}
