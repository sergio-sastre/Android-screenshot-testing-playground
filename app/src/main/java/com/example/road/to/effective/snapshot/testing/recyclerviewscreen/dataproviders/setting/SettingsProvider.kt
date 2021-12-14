package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.setting

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language

interface SettingsProvider {

    fun getActiveLangs() : Set<Language>

    fun setActiveLangs(activeLangs : Set<Language>)

    fun toggleLangState(language: Language)
}