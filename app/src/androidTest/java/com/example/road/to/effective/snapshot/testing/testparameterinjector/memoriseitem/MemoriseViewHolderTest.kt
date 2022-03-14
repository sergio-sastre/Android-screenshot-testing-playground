package com.example.road.to.effective.snapshot.testing.testparameterinjector.memoriseitem

import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath
import com.example.road.to.effective.snapshot.testing.utils.snapshotter.MemoriseRowViewSnapshotHelper
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.Orientation

@RunWith(TestParameterInjector::class)
class MemoriseViewHolderTest : ScreenshotTest {

    enum class MemoriseViewHolderTestItem(val memoriseItem: MemoriseTestItem) {
        PORTRAIT_RIGHT_ALIGNED(
            MemoriseTestItem(
                rightAligned = true,
                orientation = Orientation.PORTRAIT
            )
        ),
        PORTRAIT_LEFT_ALIGNED(
            MemoriseTestItem(
                rightAligned = false,
                orientation = Orientation.PORTRAIT
            )
        ),
        LANDSCAPE_RIGHT_ALIGNED(
            MemoriseTestItem(
                rightAligned = true,
                orientation = Orientation.LANDSCAPE
            )
        ),
        LANDSCAPE_LEFT_ALIGNED(
            MemoriseTestItem(
                rightAligned = false,
                orientation = Orientation.LANDSCAPE
            )
        )
    }

    @HappyPath
    @Test
    fun snapPortraitMemoriseItems(@TestParameter testItem: MemoriseViewHolderTestItem) {
        MemoriseRowViewSnapshotHelper.snapMemoriseViewHolder(testItem.memoriseItem)
    }
}
