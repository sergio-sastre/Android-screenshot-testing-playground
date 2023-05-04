package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder.parameterized

import android.graphics.Color.TRANSPARENT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.github.takahirom.roborazzi.captureRoboImage
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_5
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
 * Therefore, you can only take Parameterized Screenshot tests with ParameterizedRobolectricTestRunner.
 *
 * Moreover, RNG must be active. In these tests, we do it by annotating tests with @GraphicsMode(NATIVE).
 * Alternatively one could drop the annotation and enable RNG for all Robolectric tests in a module,
 * adding the following in the module's build.gradle:
 *
 *  testOptions {
 *      unitTests {
 *          includeAndroidResources = true
 *          all {
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE' // this
 *          }
 *      }
 *  }
 */
@RunWith(ParameterizedRobolectricTestRunner::class)
class TrainingViewHolderParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) {
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val rule = RobolectricActivityScenarioForViewRule(
        config = testItem.item.viewConfig,
        backgroundColor = TRANSPARENT,
        deviceScreen = PIXEL_5,
    )

    @GraphicsMode(NATIVE)
    @Test
    fun snapViewHolder() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        viewHolder.itemView
            .captureRoboImage(
                filePath("${testItem.name}_Parameterized")
            )
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class TrainingViewHolderParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) {
    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val rule = RobolectricActivityScenarioForViewRule(
        config = testItem.item.viewConfig,
        backgroundColor = TRANSPARENT,
        deviceScreen = PIXEL_5,
    )

    @GraphicsMode(NATIVE)
    @Test
    fun snapViewHolder() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        viewHolder.itemView
            .captureRoboImage(
                filePath("${testItem.name}_Parameterized")
            )
    }
}
