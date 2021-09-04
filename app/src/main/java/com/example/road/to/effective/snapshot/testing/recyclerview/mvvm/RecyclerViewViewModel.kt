package com.example.road.to.effective.snapshot.testing.recyclerview.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.MemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.MemorisesInfoProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.SettingsProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.*
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.ClickAction.ShowNotSupportedActionSnackbar
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.Event
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.MAX_TEXTS

class RecyclerViewViewModel(
    private val memoriseProvider: MemoriseProvider,
    private val settingsProvider: SettingsProvider
) : ViewModel(),
    RecyclerViewViewModelContract {

    private var memorises: MutableList<Memorise> = mutableListOf()

    private val mutableListState = MutableLiveData<MemoriseListViewState>()
    private val mutableTrainingState = MutableLiveData<TrainingViewState>()
    private val mutableClicksState = MutableLiveData<Event<ClickAction>>()

    override fun getMemoriseListViewState(): LiveData<MemoriseListViewState> = mutableListState

    override fun getTrainingViewState(): LiveData<TrainingViewState> = mutableTrainingState

    override fun getClickableActionData(): LiveData<Event<ClickAction>> = mutableClicksState

    override fun fetchMemorises() {
        memorises = memoriseProvider.getMemorises().toMutableList()
        val translByLang = MemorisesInfoProvider(memorises).getTranslationsToTrainByDestLang()

        mutableListState.value = MemoriseListViewState.Results(memorises, MAX_TEXTS)
        mutableTrainingState.value =
            TrainingViewState.Results(translByLang, settingsProvider.getActiveLangs())
    }

    override fun clickOnTrainFiltered() {
        mutableClicksState.value = Event(ShowNotSupportedActionSnackbar)
    }

    override fun clickOnFilter(lang: Language) {
        settingsProvider.toggleLangState(lang)
        val translByLang = MemorisesInfoProvider(memorises).getTranslationsToTrainByDestLang()
        mutableTrainingState.value =
            TrainingViewState.Results(translByLang, settingsProvider.getActiveLangs())
    }

    override fun clickOnMemorise(memorise: Memorise) {
        mutableClicksState.value = Event(ShowNotSupportedActionSnackbar)
    }

    override fun clickOnTrainWeakInMemorise(memorise: Memorise) {
        mutableClicksState.value = Event(ShowNotSupportedActionSnackbar)
    }

    override fun clickOnTrainAllInMemorise(memorise: Memorise) {
        mutableClicksState.value = Event(ShowNotSupportedActionSnackbar)
    }

    override fun clickOnConfirmDeleteMemorise(memorise: Memorise) {
        memorises.remove(memorise)
        val translByDestLang = MemorisesInfoProvider(memorises).getTranslationsToTrainByDestLang()
        val newLangs = translByDestLang.keys.intersect(settingsProvider.getActiveLangs())
        settingsProvider.setActiveLangs(newLangs)

        mutableListState.value = when (memorises.isEmpty()) {
            true -> MemoriseListViewState.Empty
            false -> MemoriseListViewState.Results(memorises, MAX_TEXTS)
        }

        mutableTrainingState.value =
            TrainingViewState.Results(translByDestLang, settingsProvider.getActiveLangs())
    }
}