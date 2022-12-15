package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dropshots.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import sergio.sastre.uitesting.utils.utils.waitForView

/**
 * Waits for the Ui thread to be Idle and returns [RecyclerView.ViewHolder]#itemView.
 */
fun waitForViewHolder(actionToDo: () -> RecyclerView.ViewHolder): View {
    val viewHolder = waitForView { actionToDo() }
    return viewHolder.itemView
}