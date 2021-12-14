package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.memorise

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise

interface MemoriseProvider {
    fun getMemorises(): List<Memorise>
}