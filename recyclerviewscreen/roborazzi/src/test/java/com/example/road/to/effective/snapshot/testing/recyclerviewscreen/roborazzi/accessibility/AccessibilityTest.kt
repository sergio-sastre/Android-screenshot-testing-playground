package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.accessibility

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.roborazziAccessibilityOptions
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder.MemoriseTestItemGenerator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder.parameterized.HappyPathTestItem.WITH_WORDS
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*Accessibility*'
 *
 * See results under "Project" View and HTML reports under build/reports/roborazzi/index.html
 */

/**
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
 *
 * Therefore, RNG must be active. In these tests, we do it by annotating tests with @GraphicsMode(NATIVE).
 * Alternatively one could drop the annotation and enable RNG for all Robolectric tests in a module,
 * adding the corresponding system property in the module's build.gradle.
 *
 *  testOptions {
 *      unitTests {
 *          includeAndroidResources = true
 *          all {
 *              systemProperty 'robolectric.graphicsMode', 'NATIVE' // this
 *          }
 *      }
 *  }
 *
 *  That's how the experimental Robolectric feature "hardware rendering" is enabled in this module,
 *  which enables rendering of shadows and elevation.
 *  You can delete it in the build.gradle:
 *
 *  systemProperty 'robolectric.pixelCopyRenderMode', 'hardware'
 */
@RunWith(RobolectricTestRunner::class)
class AccessibilityTest {

    @get:Rule
    val activityScenarioForViewRule =
        RobolectricActivityScenarioForViewRule(
            deviceScreen = DeviceScreen.Phone.PIXEL_4A,
        )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapMemoriseVHWithAccessibility() {
        val activity = activityScenarioForViewRule.activity
        val layout = activityScenarioForViewRule.inflateAndWaitForIdle(R.layout.memorise_row)

        val viewHolder = waitForMeasuredViewHolder {
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(
                    MemoriseTestItemGenerator.generateMemoriseItem(
                        rightAligned = false,
                        activity = activity
                    )
                )
            }
        }

        viewHolder
            .itemView
            .captureRoboImage(
                filePath = filePath("MemoriseViewHolder_Accessibility"),
                roborazziOptions = roborazziAccessibilityOptions,
            )
    }

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [31])
    @Test
    fun snapTrainingVHWithAccessibility() {
        val layout = activityScenarioForViewRule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = WITH_WORDS.item.trainingItem, languageClickedListener = null)
            }
        }

        viewHolder.itemView
            .captureRoboImage(
                filePath = filePath("TrainingViewHolder_Accessibility"),
                roborazziOptions = roborazziAccessibilityOptions,
            )
    }
}
