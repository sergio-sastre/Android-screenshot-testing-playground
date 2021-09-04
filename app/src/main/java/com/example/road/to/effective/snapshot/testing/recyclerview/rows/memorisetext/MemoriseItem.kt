package com.example.road.to.effective.snapshot.testing.recyclerview.rows.memorisetext

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowType

data class MemoriseItem(
    val memorise: Memorise,
    val rightAligned: Boolean,
    val currentTime: Long) : RowType {

    override fun isTheSame(newItem: RowType): Boolean =
        this.memorise.id == (newItem as? MemoriseItem)?.memorise?.id

    override fun isContentTheSame(newItem: RowType): Boolean =
        this.rightAligned == (newItem as? MemoriseItem)?.rightAligned
}