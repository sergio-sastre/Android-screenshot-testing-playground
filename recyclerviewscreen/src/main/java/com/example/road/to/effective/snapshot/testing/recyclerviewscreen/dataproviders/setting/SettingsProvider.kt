package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.setting

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language

interface SettingsProvider {

    fun getActiveLangs() : Set<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language>

    fun setActiveLangs(activeLangs : Set<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language>)

    fun toggleLangState(language: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language)
}