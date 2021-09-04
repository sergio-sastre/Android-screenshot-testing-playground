package com.example.road.to.effective.snapshot.testing.recyclerview.views

import com.example.road.to.effective.snapshot.testing.recyclerview.utils.asShortString

class LongAsShortTransformer : DigitTextView.LongTransformer {
    override fun convertToString(long: Long): String = long.asShortString()
}