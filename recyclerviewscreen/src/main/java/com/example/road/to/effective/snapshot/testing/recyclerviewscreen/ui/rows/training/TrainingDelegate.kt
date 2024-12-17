package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training

import android.view.ViewGroup
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.TrainAllClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RowType
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class TrainingDelegate<T>(private val listener: T?) :
    AbsListItemAdapterDelegate<TrainingItem, RowType, TrainingViewHolder>()
        where T : LanguageFilterClickedListener,
              T : TrainAllClickedListener {

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