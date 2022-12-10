package com.example.road.to.effective.snapshot.testing.recyclerview.shot.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import sergio.sastre.uitesting.utils.utils.waitForView

/**
 * Waits for the Ui thread to be Idle and calculates the expected dimensions of the
 * [RecyclerView.ViewHolder].
 * This is necessary to take a screenshot with the expected size of the dialog. For that, we need
 * to provide [RecyclerView.ViewHolder].measureHeight/measureWidth to the method used to take/verify
 * the screenshot.
 */
fun waitForViewHolder(actionToDo: () -> ViewHolder): ViewHolder {
    val viewHolder = waitForView { actionToDo() }
    viewHolder.itemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return viewHolder
}