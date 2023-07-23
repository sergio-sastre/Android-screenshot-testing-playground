package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.recyclerview

import android.view.View
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_XL
import sergio.sastre.uitesting.robolectric.fragmentscenario.robolectricFragmentScenarioConfiguratorRule
import sergio.sastre.uitesting.utils.utils.drawFullScrollableToBitmap

/**
 * Execute the command below to run only RecyclerViewTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:roborazzi:recordRoborazziDebug --tests '*RecyclerView*'
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:roborazzi:verifyRoborazziDebug --tests '*RecyclerView*'
 *
 * See results under "Project" View
 */

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
@RunWith(RobolectricTestRunner::class)
class FullRecyclerViewTest {

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        robolectricFragmentScenarioConfiguratorRule<LanguageTrainingFragment>(deviceScreen = PIXEL_XL)

    @GraphicsMode(NATIVE)
    @Config(sdk = [30])
    @Test
    fun snapFullRecyclerView() {
        val fragmentView = fragmentScenarioConfiguratorRule.fragment.requireView()
        val recyclerView: View = fragmentView.findViewById(R.id.memoriseList)

        recyclerView
            .drawFullScrollableToBitmap()
            .captureRoboImage(filePath("FullRecyclerView"))
    }
}
