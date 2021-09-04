package com.example.road.to.effective.snapshot.testing.recyclerview

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise

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