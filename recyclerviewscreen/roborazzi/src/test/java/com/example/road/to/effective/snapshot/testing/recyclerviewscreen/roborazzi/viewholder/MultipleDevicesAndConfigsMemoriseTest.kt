package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder

import android.graphics.Color.TRANSPARENT
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R.style.Theme_Custom
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder.parameterized.wordsInSomeLangsTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_4A
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Tablet.MEDIUM_TABLET
import sergio.sastre.uitesting.robolectric.utils.view.TestDataForView
import sergio.sastre.uitesting.robolectric.utils.view.TestDataForViewCombinator
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.FontSize.SMALL
import sergio.sastre.uitesting.utils.common.UiMode.DAY
import sergio.sastre.uitesting.utils.common.UiMode.NIGHT
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*MultipleDevicesAndConfigs*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*MultipleDevicesAndConfigs*'
 *
 * See results under "Project" View and HTML reports under build/reports/roborazzi/index.html
 */

/**
 * You can only take Parameterized Screenshot tests with ParameterizedRobolectricTestRunner.
 *
 * Roborazzi requires Robolectric Native Graphics (RNG) to generate screenshots.
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
class MultipleDevicesAndConfigsMemoriseTest(
    private val testItem: TestDataForView<TrainingUiState>,
) {
    enum class TrainingUiState(val value: TrainingItem) {
        WITHOUT_WORDS(TrainingItem(emptyMap(), emptySet())),
        WITH_WORDS(wordsInSomeLangsTrainingItem),
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<TestDataForView<TrainingUiState>> =
            TestDataForViewCombinator(
                uiStates = TrainingUiState.entries.toTypedArray()
            )
                .forDevices(
                    PIXEL_4A,
                    MEDIUM_TABLET,
                )
                .forConfigs(
                    ViewConfigItem(uiMode = DAY, fontSize = SMALL),
                    ViewConfigItem(uiMode = DAY, theme = Theme_Custom, locale = "ar"),
                    ViewConfigItem(uiMode = NIGHT),
                )
                .combineAll()
    }

    @get:Rule
    val rule = RobolectricActivityScenarioForViewRule(
        config = testItem.config,
        backgroundColor = TRANSPARENT,
        deviceScreen = testItem.device,
    )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapViewHolder() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.uiState.value, languageClickedListener = null)
            }
        }

        viewHolder.itemView
            .captureRoboImage(
                filePath("${testItem.screenshotId}_Parameterized")
            )
    }
}
