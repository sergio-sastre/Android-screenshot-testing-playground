package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingViewModelContract.MemoriseListViewState
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.LanguageTrainingViewModelContract.TrainingViewState
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm.ViewModelStateProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderType
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem

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