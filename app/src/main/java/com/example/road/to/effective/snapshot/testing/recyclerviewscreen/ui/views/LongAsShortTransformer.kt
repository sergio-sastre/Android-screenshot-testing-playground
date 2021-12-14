package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.views

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.asShortString

class LongAsShortTransformer : DigitTextView.LongTransformer {
    override fun convertToString(long: Long): String = long.asShortString()
}