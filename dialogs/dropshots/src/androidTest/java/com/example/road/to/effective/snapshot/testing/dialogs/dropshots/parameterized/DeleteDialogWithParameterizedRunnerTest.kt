package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.parameterized

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.dropshots.waitForDialogView
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments). Therefore, we need to create 2 different classes to separate @UnhappyPath and
 * @HappyPath tests
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+
 */
@RunWith(Parameterized::class)
class DeleteDialogParameterizedHappyPathTest(
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
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @HappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForDialogView {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(config = testItem.deleteItem.viewConfig)

    @UnhappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForDialogView {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, testItem.deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}
