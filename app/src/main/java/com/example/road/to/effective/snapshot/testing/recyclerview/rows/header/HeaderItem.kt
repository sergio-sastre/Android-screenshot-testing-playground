package com.example.road.to.effective.snapshot.testing.recyclerview.rows.header

import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowType

data class HeaderItem(val headerType: HeaderType) : RowType {
    override fun isTheSame(newItem: RowType): Boolean = newItem is HeaderItem

    override fun isContentTheSame(newItem: RowType): Boolean =
        (newItem as? HeaderItem)?.headerType == headerType
}

enum class HeaderType {
    TRAINING
}