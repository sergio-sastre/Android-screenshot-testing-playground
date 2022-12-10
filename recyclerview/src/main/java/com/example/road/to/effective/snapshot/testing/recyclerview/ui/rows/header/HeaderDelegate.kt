package com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.header

import android.view.ViewGroup
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.RowType
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class HeaderDelegate : AbsListItemAdapterDelegate<HeaderItem, RowType, HeaderItemViewHolder>() {

    override fun isForViewType(item: RowType, items: MutableList<RowType>, position: Int): Boolean =
        item is HeaderItem

    override fun onCreateViewHolder(parent: ViewGroup): HeaderItemViewHolder =
        HeaderItemViewHolder(parent.inflate(R.layout.header_row))

    override fun onBindViewHolder(
        item: HeaderItem,
        holder: HeaderItemViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

}