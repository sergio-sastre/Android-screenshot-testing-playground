package com.example.road.to.effective.snapshot.testing.recyclerview.rows

interface RowType {

    data class Payload(val oldItem: RowType, val newItem: RowType)

    fun isTheSame(newItem: RowType): Boolean

    fun isContentTheSame(newItem: RowType): Boolean

    fun getChangePayLoad(newItem: RowType) : Any? =
        Payload(this, newItem)
}