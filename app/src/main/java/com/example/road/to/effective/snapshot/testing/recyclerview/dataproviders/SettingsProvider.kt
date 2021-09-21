package com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language

interface SettingsProvider {

    fun getActiveLangs() : Set<Language>

    fun setActiveLangs(activeLangs : Set<Language>)

    fun toggleLangState(language: Language)
}