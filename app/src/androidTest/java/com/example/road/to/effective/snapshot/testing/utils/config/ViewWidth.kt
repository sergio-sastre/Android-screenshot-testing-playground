package com.example.road.to.effective.snapshot.testing.utils.config

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.px

enum class ViewWidth(val widthInPx: Int?) {
    NARROW(360.px()),
    WIDE(600.px()),
    DEVICE_WIDTH(null) // Shot takes device width if null
}