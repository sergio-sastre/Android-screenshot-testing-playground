package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import dev.testify.annotation.ScreenshotInstrumentation
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForView
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.android_testify.setScreenshotFirstView
import sergio.sastre.uitesting.utils.activityscenario.ViewConfigItem
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule

/**
 * Execute the command below to run only ViewHolderTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotRecord -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:android-testify:screenshotTest -PscreenshotAnnotation=com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest
 *
 * WARNING: filtering tests by custom annotation not working with Gradle Managed Devices)
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example with ActivityTestRules of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        exactness = 0.85f,
        config = ViewConfigItem(
            uiMode = UiMode.DAY,
            locale = "en",
            orientation = Orientation.PORTRAIT,
        ),
    )

    @ScreenshotInstrumentation
    @HappyPath
    @ViewHolderTest
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
            .setScreenshotFirstView()
            .assertSame(name = "MemoriseViewHolderHappy")
    }
}

class MemoriseViewHolderUnhappyPathTest {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        exactness = 0.85f,
        config = ViewConfigItem(
            uiMode = UiMode.NIGHT,
            locale = "en_XA",
            fontSize = FontSize.HUGE,
            displaySize = DisplaySize.LARGEST,
            orientation = Orientation.LANDSCAPE,
        ),
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ViewHolderTest
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
            .setScreenshotFirstView()
            .assertSame(name = "MemoriseViewHolderUnhappy")
    }
}
