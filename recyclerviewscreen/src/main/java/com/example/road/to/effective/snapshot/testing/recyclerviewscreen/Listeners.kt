package com.example.road.to.effective.snapshot.testing.recyclerviewscreen

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise

interface DeleteMemoriseListener {
    fun onMemoriseDeleted(memorise: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise)
}

interface MemoriseClickedListener {
    fun onMemoriseClicked(memorise: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise)
}

interface LanguageFilterClickedListener {
    fun onLanguageFilterClicked(lang: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language)
}

interface TrainAllClickedListener {
    fun onTrainAllClicked()
}