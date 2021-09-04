package com.example.road.to.effective.snapshot.testing.recyclerview.rows.training

import android.view.ViewGroup
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowType
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class TrainingDelegate(val listener: LanguageFilterClickedListener) : AbsListItemAdapterDelegate<TrainingItem, RowType, TrainingViewHolder>() {
    override fun isForViewType(item: RowType, items: MutableList<RowType>, position: Int): Boolean =
        item is TrainingItem

    override fun onCreateViewHolder(parent: ViewGroup): TrainingViewHolder =
        TrainingViewHolder(parent.inflate(R.layout.training_row))

    override fun onBindViewHolder(
        item: TrainingItem,
        holder: TrainingViewHolder,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            (payloads.firstOrNull() as? TrainingItemPayload)?.run {
                holder.update(this)
            }
        } else {
            holder.bind(item, listener)
        }
    }
}