package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.memorise.MemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.setting.SettingsProvider

class RecyclerViewViewModelFactory(
    private val memoriseProvider: MemoriseProvider,
    private val settingsProvider: SettingsProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RecyclerViewViewModel(memoriseProvider, settingsProvider) as T
}