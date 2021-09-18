package com.example.road.to.effective.snapshot.testing.utils.config

import com.example.road.to.effective.snapshot.testing.recyclerview.utils.px

enum class ViewWidth(val widthInPx: Int?) {
    NARROW(360.px()),
    WIDE(600.px()),
    DEVICE_WIDTH(null)
}