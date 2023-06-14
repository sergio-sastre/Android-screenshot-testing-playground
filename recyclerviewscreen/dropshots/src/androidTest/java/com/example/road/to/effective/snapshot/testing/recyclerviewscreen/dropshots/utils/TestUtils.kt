package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.utils

import android.graphics.Bitmap
import android.view.View
import androidx.core.view.drawToBitmap
import sergio.sastre.uitesting.utils.utils.waitForMeasuredView

fun drawFullScrollableToBitmap(scrollableView: () -> View): Bitmap =
    waitForMeasuredView { scrollableView() }.drawToBitmap()