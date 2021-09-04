package com.example.road.to.effective.snapshot.testing.parameterized

import androidx.test.core.app.ActivityScenario
import com.example.road.to.effective.snapshot.testing.MainActivity
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language.*
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.utils.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.inflate
import com.example.road.to.effective.snapshot.testing.utils.waitForActivity
import com.example.road.to.effective.snapshot.testing.utils.waitForView
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.FontScaleRules

@RunWith(Parameterized::class)
class TrainingItemTest(private val testItem: TrainingTestItem) : ScreenshotTest {

    @get:Rule
    val fontSize = FontScaleRules.fontScaleTestRule(testItem.fontScale)

    companion object {
        fun translation(): Translation =
            Translation("word", setOf(IntRange(0, 4)), English, English)

        fun translations(amount: Int): List<Translation> {
            val translation = translation()
            return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
        }

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TrainingTestItem> =
            arrayOf(
                TrainingTestItem(
                    FontScale.NORMAL,
                    DialogTheme.MATERIAL_LIGHT_DIALOG,
                    TrainingItem(
                        trainingByLang = mapOf(
                            English to translations(2),
                            Russian to translations(5),
                            German to translations(1)
                        ),
                        activeLangs = setOf(Russian, German)
                    ),
                    "SMOKE"
                ),
                TrainingTestItem(
                    FontScale.NORMAL,
                    DialogTheme.MATERIAL_DARK,
                    TrainingItem(
                        trainingByLang = mapOf(),
                        activeLangs = emptySet()
                    ),
                    "EMPTY"
                ),
                TrainingTestItem(
                    FontScale.HUGE,
                    DialogTheme.MATERIAL_LIGHT,
                    TrainingItem(
                        trainingByLang = mapOf(
                            English to translations(999_999),
                            Russian to translations(999_999),
                            Portuguese to translations(999_999),
                            French to translations(999_999),
                            German to translations(999_999),
                            Spanish to translations(999_999),
                            Italian to translations(999_999)
                        ),
                        activeLangs = Language.values().toSet()
                    ),
                    "EXTREME"
                )
            )
    }

    @Test
    fun snapDialog() {
        snapViewHolder(testItem)
    }
}

class TrainingTestItem(
    val fontScale: FontScale,
    val theme: DialogTheme,
    val trainingItem: TrainingItem,
    val testName: String
)

private fun ScreenshotTest.snapViewHolder(testItem: TrainingTestItem) {
    val activity = ActivityScenario.launch(MainActivity::class.java)
        .waitForActivity(testItem.theme.themId)

    val view = waitForView {
        val layout = activity.inflate(R.layout.training_row)
        TrainingViewHolder(layout).apply {
            bind(testItem.trainingItem, null)
        }
    }

    compareScreenshot(holder = view, heightInPx = 600, name = testItem.testName)
}
