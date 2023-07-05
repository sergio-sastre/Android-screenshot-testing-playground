package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import androidx.lifecycle.LiveData
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.Event

interface LanguageTrainingViewModelContract : ViewModelStateProvider {

    fun getClickableActionData(): LiveData<Event<ClickAction>>

    fun fetchMemorises()
    fun clickOnTrainFiltered()
    fun clickOnTrainAll()
    fun clickOnFilter(lang: Language)
    fun clickOnMemorise(memorise: Memorise)
    fun clickOnTrainWeakInMemorise(memorise: Memorise)
    fun clickOnTrainAllInMemorise(memorise: Memorise)
    fun clickOnDeleteMemorise(memorise: Memorise)

    sealed class MemoriseListViewState {
        object Loading : MemoriseListViewState()
        object Error : MemoriseListViewState()
        object Empty : MemoriseListViewState()
        data class Results(val memorises: List<Memorise>) :
            MemoriseListViewState()
    }

    sealed class TrainingViewState {
        object Loading : TrainingViewState()
        object Empty : TrainingViewState()
        data class Results(
            val trainingByLang: Map<Language, List<Translation>>,
            val activeLangs: Set<Language>
        ) : TrainingViewState()
    }

    sealed class ClickAction {
        object ShowNotSupportedActionSnackbar : ClickAction()
    }
}