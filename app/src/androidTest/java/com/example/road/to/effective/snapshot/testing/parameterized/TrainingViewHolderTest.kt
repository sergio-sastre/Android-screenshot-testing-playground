package com.example.road.to.effective.snapshot.testing.parameterized

import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language.*
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.utils.SmokeTest
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import com.example.road.to.effective.snapshot.testing.utils.inflate
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withWordsToTrainHappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withWordsToTrainUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withoutWordsToTrainHappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TrainingTestItemObjectMother.withoutWordsToTrainUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.TranslationsObjectMother.translations
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.FontSizeActivityScenario.launchWith

@RunWith(Parameterized::class)
class TrainingItemUnhappyPathTest(private val testItem: TrainingTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TrainingTestItem> =
            arrayOf(
                withWordsToTrainUnhappyPath(),
                withoutWordsToTrainUnhappyPath(ViewWidth.NARROW),
                withoutWordsToTrainUnhappyPath(ViewWidth.WIDE),
            )
    }

    @Test
    fun snapTrainingItem() {
        snapViewHolder(testItem)
    }
}

@RunWith(Parameterized::class)
class TrainingItemHappyPathTest(private val testItem: TrainingTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
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

    @SmokeTest
    @Test
    fun snapTrainingItem() {
        snapViewHolder(testItem)
    }
}

class TrainingTestItem(
    val fontScale: FontScale,
    val viewWidth: ViewWidth,
    val trainingItem: TrainingItem,
    val testName: String
)


private fun ScreenshotTest.snapViewHolder(testItem: TrainingTestItem) {
    val activity = launchWith(testItem.fontScale)
        .waitForActivity()

    val view = waitForView {
        val layout = activity.inflate(R.layout.training_row)
        TrainingViewHolder(layout).apply {
            bind(item = testItem.trainingItem, languageClickedListener = null)
        }
    }

    compareScreenshot(
        holder = view,
        heightInPx = 600,
        widthInPx = testItem.viewWidth.widthInPx,
        name = testItem.testName
    )
}
