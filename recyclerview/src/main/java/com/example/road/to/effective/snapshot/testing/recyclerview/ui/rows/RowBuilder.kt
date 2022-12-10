package com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows

import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.MemoriseListViewState
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract.TrainingViewState
import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.ViewModelStateProvider
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.header.HeaderItem
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.header.HeaderType
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingItem

class RowBuilder(private val viewModelStateProvider: ViewModelStateProvider) {

    private fun trainingRows(): List<RowType> =
        viewModelStateProvider.getTrainingViewState().value.toRows()

    private fun memoriseRows(): List<RowType> =
        viewModelStateProvider.getMemoriseListViewState().value.toRows()

    fun fullScreenRows() = trainingRows() + memoriseRows()

    private fun TrainingViewState?.toRows(): List<RowType> =
        this?.let {
            when (this) {
                TrainingViewState.Loading -> TODO()
                TrainingViewState.Empty -> TODO()
                is TrainingViewState.Results -> {
                    listOf(
                        HeaderItem(HeaderType.TRAINING),
                        TrainingItem(trainingByLang, activeLangs)
                    )
                }
            }
        } ?: emptyList()


    private fun MemoriseListViewState?.toRows(): List<RowType> =
        this?.let {
            when (this) {
                MemoriseListViewState.Loading -> TODO()
                MemoriseListViewState.Error -> TODO()
                MemoriseListViewState.Empty -> emptyList()

                is MemoriseListViewState.Results ->
                    memorises.mapIndexed { index, memorise ->
                        MemoriseItem(
                            memorise = memorise,
                            rightAligned = (index % 2 != 0),
                            currentTime = System.currentTimeMillis()
                        )
                    }
            }
        } ?: emptyList()

}