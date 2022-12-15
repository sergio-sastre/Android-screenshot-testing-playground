package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class RecyclerViewAsyncDiffAdapter(
    callback: DiffUtil.ItemCallback<RowType>,
    vararg delegates: AdapterDelegate<List<RowType>>
) : AsyncListDifferDelegationAdapter<RowType>(callback) {

    init {
        delegatesManager.run { delegates.map { addDelegate(it) } }
    }
}
