package com.example.road.to.effective.snapshot.testing.recyclerviewscreen

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise

interface DeleteMemoriseListener {
    fun onMemoriseDeleted(memorise: Memorise)
}

interface MemoriseClickedListener {
    fun onMemoriseClicked(memorise: Memorise)
}

interface LanguageFilterClickedListener {
    fun onLanguageFilterClicked(lang: Language)
}

interface TrainAllClickedListener {
    fun onTrainAllClicked()
}