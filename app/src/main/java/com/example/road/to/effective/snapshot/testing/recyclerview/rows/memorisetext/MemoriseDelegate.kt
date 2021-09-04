package com.example.road.to.effective.snapshot.testing.recyclerview.rows.memorisetext

import android.os.Handler
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerview.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowType
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MemoriseDelegate<T>(val listeners: T) :
    AbsListItemAdapterDelegate<MemoriseItem, RowType, MemoriseViewHolder<T>>()
        where T : DeleteMemoriseListener, T : MemoriseClickedListener {
    override fun isForViewType(item: RowType, items: MutableList<RowType>, position: Int): Boolean =
        item is MemoriseItem

    override fun onCreateViewHolder(parent: ViewGroup): MemoriseViewHolder<T> =
        MemoriseViewHolder(parent.inflate(R.layout.memorise_row), listeners)

    override fun onBindViewHolder(
        item: MemoriseItem,
        holder: MemoriseViewHolder<T>,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty().not()) {
            holder.update(item)
        } else {
            holder.bind(item)
        }
    }
}