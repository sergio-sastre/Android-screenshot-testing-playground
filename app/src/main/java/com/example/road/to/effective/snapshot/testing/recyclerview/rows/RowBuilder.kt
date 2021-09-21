package com.example.road.to.effective.snapshot.testing.recyclerview.rows

import com.example.road.to.effective.snapshot.testing.recyclerview.mvvm.RecyclerViewViewModelContract
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.header.HeaderItem
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.header.HeaderType
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingItem

class RowBuilder(private val viewModel: RecyclerViewViewModelContract) {

    fun trainingRows(): List<RowType> = viewModel.getTrainingViewState().value.toRows()

    fun memoriseRows(): List<RowType> = viewModel.getMemoriseListViewState().value.toRows()

    private fun RecyclerViewViewModelContract.TrainingViewState?.toRows(): List<RowType> =
        this?.let {
            when (this) {
                RecyclerViewViewModelContract.TrainingViewState.Loading -> TODO()
                RecyclerViewViewModelContract.TrainingViewState.Empty -> TODO()
                is RecyclerViewViewModelContract.TrainingViewState.Results -> {
                    listOf(
                        HeaderItem(HeaderType.TRAINING),
                        TrainingItem(trainingByLang, activeLangs)
                    )
                }
            }
        } ?: emptyList()


    private fun RecyclerViewViewModelContract.MemoriseListViewState?.toRows(): List<RowType> =
        this?.let {
            when (this) {
                RecyclerViewViewModelContract.MemoriseListViewState.Loading -> TODO()
                RecyclerViewViewModelContract.MemoriseListViewState.Error -> TODO()
                RecyclerViewViewModelContract.MemoriseListViewState.Empty ->
                    listOf(
                        //AllTextHeaderItem(0, MAX_TEXTS),
                        //EmptyMemoriseItem()
                    )

                is RecyclerViewViewModelContract.MemoriseListViewState.Results ->
                    //listOf(AllTextHeaderItem(memorises.size, MAX_TEXTS)) +
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