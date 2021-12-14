package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import androidx.lifecycle.LiveData
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewViewModelContract.MemoriseListViewState
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.RecyclerViewViewModelContract.TrainingViewState

interface ViewModelStateProvider {
    fun getMemoriseListViewState(): LiveData<MemoriseListViewState>
    fun getTrainingViewState(): LiveData<TrainingViewState>
}