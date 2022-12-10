package com.example.road.to.effective.snapshot.testing.dialogs.dropshots

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.framework.DropshotsTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
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

    @HappyPath @DropshotsTest
    @Test
    fun snapDialogHappyPath() {
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

    @UnhappyPath @DropshotsTest
    @Test
    fun snapDialogUnhappyPath() {
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
