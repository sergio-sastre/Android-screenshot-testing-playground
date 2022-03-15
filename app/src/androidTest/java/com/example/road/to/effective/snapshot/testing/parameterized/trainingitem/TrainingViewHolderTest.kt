package com.example.road.to.effective.snapshot.testing.parameterized.trainingitem

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.German
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.Russian
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withWordsToTrainHappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withWordsToTrainUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withoutWordsToTrainHappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withoutWordsToTrainUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TranslationsObjectMother.translations
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.TrainingViewSnapshotHelper
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class TrainingItemUnhappyPath(private val testItem: TrainingTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameters
        fun data(): Array<TrainingTestItem> =
            arrayOf(
                withWordsToTrainUnhappyPath(),
                withoutWordsToTrainUnhappyPath(ViewWidth.NARROW),
                withoutWordsToTrainUnhappyPath(ViewWidth.WIDE),
            )
    }

    @UnhappyPath
    @Test
    fun snapTrainingItem() {
        TrainingViewSnapshotHelper.snapTrainingViewHolder(testItem)
    }
}

@RunWith(Parameterized::class)
class TrainingItemHappyPath(private val testItem: TrainingTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameters
        fun data(): Array<TrainingTestItem> =
            arrayOf(
                withoutWordsToTrainHappyPath(),
                withWordsToTrainHappyPath(
                    TrainingItem(
                        trainingByLang = mapOf(
                            English to translations(3),
                            Russian to translations(5),
                            German to translations(1)
                        ),
                        activeLangs = setOf(Russian, German)
                    )
                )
            )
    }

    @HappyPath
    @Test
    fun snapTrainingItem() {
        TrainingViewSnapshotHelper.snapTrainingViewHolder(testItem)
    }
}
