package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

object PopUpWindowBuilder {

    fun buildActionItemWindow(layout: View, width: Int): PopupWindow =
        PopupWindow(layout, width, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            elevation = 4.px().toFloat()
            isOutsideTouchable = false
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            isFocusable = true
        }

}