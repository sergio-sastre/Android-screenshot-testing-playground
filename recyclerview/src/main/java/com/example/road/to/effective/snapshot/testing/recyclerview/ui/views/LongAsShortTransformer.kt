package com.example.road.to.effective.snapshot.testing.recyclerview.ui.views

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.asShortString

class LongAsShortTransformer(
    private val context: Context
) : DigitTextView.LongTransformer {
    override fun convertToString(long: Long): String = long.asShortString(context)
}