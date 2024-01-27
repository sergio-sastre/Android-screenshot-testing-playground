package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.android_testify.viewholder.parameterized

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.ViewHolderTest
import dev.testify.TestifyFeatures.GenerateDiffs
import dev.testify.annotation.ScreenshotInstrumentation
import sergio.sastre.uitesting.android_testify.ScreenshotRuleWithConfigurationForView
import sergio.sastre.uitesting.android_testify.assertSame
import sergio.sastre.uitesting.android_testify.setScreenshotFirstView
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
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd
 * 2. Verify (move recorded screenshot files first -> https://ndtp.github.io/android-testify/docs/recipes/gmd):
 *    ./gradlew :recyclerviewscreen:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage
 *
 * WARNING: filtering tests by custom annotation not working with Gradle Managed Devices)
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+, throwing
 * java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder error in lower APIs
 */
@RunWith(Parameterized::class)
class TrainingViewHolderParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    private val viewConfig
        get() = testItem.item.viewConfig

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        exactness = 0.85f,
        config = viewConfig
    )

    @ScreenshotInstrumentation
    @HappyPath
    @ViewHolderTest
    @Test
    fun snapViewHolder() {
        screenshotRule
            .setTargetLayoutId(R.layout.training_row)
            .setViewModifications { targetLayout ->
                TrainingViewHolder(targetLayout).apply {
                    bind(
                        item = testItem.item.trainingItem,
                        languageClickedListener = null,
                    )
                }
            }
            .setScreenshotFirstView()
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "${testItem.name}_Parameterized"
            )
    }
}

@RunWith(Parameterized::class)
class TrainingViewHolderParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    private val viewConfig
        get() = testItem.item.viewConfig

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    var screenshotRule = ScreenshotRuleWithConfigurationForView(
        exactness = 0.85f,
        config = viewConfig
    )

    @ScreenshotInstrumentation
    @UnhappyPath
    @ViewHolderTest
    @Test
    fun snapViewHolder() {
        screenshotRule
            .setTargetLayoutId(R.layout.training_row)
            .setViewModifications { targetLayout ->
                TrainingViewHolder(targetLayout).apply {
                    bind(
                        item = testItem.item.trainingItem,
                        languageClickedListener = null,
                    )
                }
            }
            .setScreenshotFirstView()
            .withExperimentalFeatureEnabled(GenerateDiffs)
            .assertSame(
                name = "${testItem.name}_Parameterized"
            )
    }
}
