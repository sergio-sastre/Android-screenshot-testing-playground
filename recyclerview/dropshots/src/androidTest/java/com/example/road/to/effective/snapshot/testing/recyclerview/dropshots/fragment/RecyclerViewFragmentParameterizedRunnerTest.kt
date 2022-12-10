package com.example.road.to.effective.snapshot.testing.recyclerview.dropshots.fragment

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewFragment
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Rule
import org.junit.Test
import sergio.sastre.uitesting.utils.fragmentscenario.fragmentScenarioConfiguratorRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

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
class RecyclerViewFragmentParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<RecyclerViewFragment>(config = testItem.item)

    @HappyPath
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.view!!,
            name = "RecyclerViewFragment_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class RecyclerViewFragmentParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {
    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val fragmentScenarioConfiguratorRule =
        fragmentScenarioConfiguratorRule<RecyclerViewFragment>(config = testItem.item)

    @UnhappyPath
    @Test
    fun snapFragment() {
        dropshots.assertSnapshot(
            view = fragmentScenarioConfiguratorRule.fragment.view!!,
            name = "RecyclerViewFragment_${testItem.name}_Parameterized",
        )
    }
}
