package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.activity

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewActivity
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils.filePath
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.GraphicsMode.Mode.NATIVE
import sergio.sastre.uitesting.robolectric.activityscenario.robolectricActivityScenarioForActivityRule
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen.Phone.PIXEL_5

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
class RecyclerViewActivityParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<RecyclerViewActivity>(
            config = testItem.item,
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Test
    fun snapActivity() {
        activityScenarioForActivityRule
            .rootView
            .captureRoboImage(
                filePath("RecyclerViewActivity_${testItem.name}_Parameterized")
            )
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class RecyclerViewActivityParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val activityScenarioForActivityRule =
        robolectricActivityScenarioForActivityRule<RecyclerViewActivity>(
            config = testItem.item,
            deviceScreen = PIXEL_5,
        )

    @GraphicsMode(NATIVE)
    @Test
    fun snapActivity() {
        activityScenarioForActivityRule
            .rootView
            .captureRoboImage(
                filePath("RecyclerViewActivity_${testItem.name}_Parameterized")
            )
    }
}
