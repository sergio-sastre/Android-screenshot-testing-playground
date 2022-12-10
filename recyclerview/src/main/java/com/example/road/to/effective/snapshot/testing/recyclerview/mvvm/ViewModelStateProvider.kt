package com.example.road.to.effective.snapshot.testing.recyclerview.mvvm

import androidx.lifecycle.LiveData
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.MemoriseListViewState
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.TrainingViewState

interface ViewModelStateProvider {
    fun getMemoriseListViewState(): LiveData<MemoriseListViewState>
    fun getTrainingViewState(): LiveData<TrainingViewState>
}