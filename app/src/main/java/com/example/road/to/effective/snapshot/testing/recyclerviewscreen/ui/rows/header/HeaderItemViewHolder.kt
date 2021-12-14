package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.R

class HeaderItemViewHolder(
    private val container: View,
) : RecyclerView.ViewHolder(container) {
    val title: TextView = container.findViewById(R.id.title)

    fun bind(item: HeaderItem) {
        val textSource = when (item.headerType) {
            HeaderType.TRAINING -> R.string.training_header
        }
        title.text = title.context.getString(textSource)
    }
}