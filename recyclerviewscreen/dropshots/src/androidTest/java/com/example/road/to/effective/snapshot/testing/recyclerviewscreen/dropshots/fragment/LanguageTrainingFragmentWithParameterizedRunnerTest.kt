package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.fragment

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.utils.DropshotsAPI29Fix
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingFragment
import com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Execute the command below to run only FragmentTests
 * 1. Record:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest -Pdropshots.record
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen:dropshots:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.example.road.to.effective.snapshot.testing.testannotations.FragmentTest
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
class LanguageTrainingFragmentParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>(config = testItem.item)

    @HappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.requireView(),
            name = "LanguageTrainingFragment_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class LanguageTrainingFragmentParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<LanguageTrainingFragment>(config = testItem.item)

    @UnhappyPath
    @FragmentTest
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.requireView(),
            name = "LanguageTrainingFragment_${testItem.name}_Parameterized",
        )
    }
}
