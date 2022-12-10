package com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.setting

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.addOrRemove

class UserSettingsProvider : SettingsProvider {

    private var activeLangsNow = setOf(Language.Russian)

    override fun getActiveLangs(): Set<Language> {
        return activeLangsNow
    }

    override fun setActiveLangs(activeLangs: Set<Language>) {
        activeLangsNow = activeLangs
    }

    override fun toggleLangState(language: Language) {
        activeLangsNow = activeLangsNow.addOrRemove(language)
    }
}