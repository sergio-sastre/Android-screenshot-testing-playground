package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.activityscenario

import com.example.road.to.effective.snapshot.testing.parameterized.deletedialog.DeleteDialogTestItem
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.spaciousDialogUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.suffocatedItemUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper.snapDeleteDialogWithActivityScenario
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DeleteDialogWithFontSizeActivityScenarioTest(private val testItem: DeleteDialogTestItem) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<DeleteDialogTestItem> =
            arrayOf(
                spaciousDialogUnhappyPath(),
                suffocatedItemUnhappyPath()
            )
    }

    @UnhappyPath
    @Test
    fun snapDialog() {
        snapDeleteDialogWithActivityScenario(testItem)
    }
}