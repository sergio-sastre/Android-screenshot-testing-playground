package com.example.road.to.effective.snapshot.testing.testparameterinjector.deletedialog

import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.normalItemHappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.spaciousDialogUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.objectmother.DeleteDialogTestItemObjectMother.suffocatedItemUnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.DeleteDialogSnapshotHelper.snapDeleteDialog
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class DeleteDialogTest : ScreenshotTest {

    enum class UnhappyPathTest(val deleteItem: DeleteDialogTestItem) {
        SPACIOUS(spaciousDialogUnhappyPath()),
        SUFFOCATED(suffocatedItemUnhappyPath())
    }

    enum class HappyPathTest(val deleteItem: DeleteDialogTestItem){
        NORMAL(normalItemHappyPath())
    }

    @UnhappyPath
    @Test
    fun snapDialogUnhappyPath(@TestParameter testItem: UnhappyPathTest) {
        snapDeleteDialog(testItem.deleteItem)
    }

    @HappyPath
    @Test
    fun snapDialogHappyPath(@TestParameter testItem: HappyPathTest) {
        snapDeleteDialog(testItem.deleteItem)
    }
}
