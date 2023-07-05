package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.workshop

/**
 * Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug
 * Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug
 *
 * Execute tests in this package only by adding the following to the previous command
 * --tests '*ScreenshotTest'
 *
 * Record:
 *  ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*ScreenshotTest'
 * Verify:
 *  ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*ScreenshotTest'
 */

import android.app.Activity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingActivity
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder.parameterized.emptyTrainingItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioForViewRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder

@RunWith(RobolectricTestRunner::class)
class ActivityScreenshotTest {

    @get:Rule
    val activityScenarioRule =
        RobolectricActivityScenarioForViewRule(
            deviceScreen = DeviceScreen.Phone.PIXEL_XL,
            config = ViewConfigItem(
                locale = "en_XA",
                orientation = Orientation.PORTRAIT,
                theme = R.style.Theme_Custom,
                uiMode = UiMode.NIGHT,
                fontSize = FontSize.LARGE,
                displaySize = DisplaySize.NORMAL,
            )
        )

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Test
    fun snapActivity() {
        val layout = activityScenarioRule.inflateAndWaitForIdle(R.layout.training_row)
        val viewHolder = waitForMeasuredViewHolder {
            TrainingViewHolder(layout).apply {
                bind(
                    item = emptyTrainingItem,
                    languageClickedListener = null,
                )
            }
        }
        viewHolder.itemView.captureRoboImage(
            filePath("trainingVH")
        )
    }
}