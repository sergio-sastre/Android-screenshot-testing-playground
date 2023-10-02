package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder

import androidx.fragment.app.FragmentActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import dev.testify.ScreenshotRule
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator.PortraitSnapshotConfiguredActivity

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
@RunWith(AndroidJUnit4::class)
class MemoriseViewHolderHappyPathTest {

    @get:Rule
    var screenshotRule =
        ScreenshotRule(PortraitSnapshotConfiguredActivity::class.java)

    @HappyPath
    @ViewHolderTest
    @ScreenshotInstrumentation
    @Test
    fun snapMemoriseViewHolderHappyPath() {

        screenshotRule
            .setTargetLayoutId(R.layout.memorise_row)
            .setViewModifications { targetLayout ->
                MemoriseViewHolder(
                    container = targetLayout,
                    itemEventListener = null,
                    animationDelay = 0L
                ).apply {
                    bind(
                        generateMemoriseItem(
                            rightAligned = false,
                            activity = screenshotRule.activity
                        )
                    )
                }
            }
            .setScreenshotViewProvider {
                it.getChildAt(0)
            }
            .assertSame()
    }
}
