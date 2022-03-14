package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.views

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.mainLocale
import java.text.NumberFormat

class NoTransformer(
    private val context: Context
) : DigitTextView.LongTransformer {
    override fun convertToString(long: Long): String =
        NumberFormat.getInstance(context.mainLocale()).format(long)
}
