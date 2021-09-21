package com.example.road.to.effective.snapshot.testing.recyclerview.mvvm

import androidx.lifecycle.LiveData
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.Event

interface RecyclerViewViewModelContract {
    fun getMemoriseListViewState(): LiveData<MemoriseListViewState>
    fun getTrainingViewState(): LiveData<TrainingViewState>
    fun getClickableActionData(): LiveData<Event<ClickAction>>

    fun fetchMemorises()
    fun clickOnTrainFiltered()
    fun clickOnFilter(lang: Language)
    fun clickOnMemorise(memorise: Memorise)
    fun clickOnTrainWeakInMemorise(memorise: Memorise)
    fun clickOnTrainAllInMemorise(memorise: Memorise)
    fun clickOnConfirmDeleteMemorise(memorise: Memorise)

    sealed class MemoriseListViewState {
        object Loading : MemoriseListViewState()
        object Error : MemoriseListViewState()
        object Empty : MemoriseListViewState()
        data class Results(val memorises: List<Memorise>, val maxTexts: Int) :
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