package com.example.road.to.effective.snapshot.testing.recyclerview.rows.training

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.rows.RowType

data class TrainingItem(
    val trainingByLang: Map<Language, List<Translation>>,
    val activeLangs: Set<Language>
) : RowType {
    override fun isTheSame(newItem: RowType): Boolean = newItem is TrainingItem

    override fun isContentTheSame(newItem: RowType): Boolean =
        (newItem as TrainingItem).trainingByLang == trainingByLang &&
                newItem.activeLangs == activeLangs

    override fun getChangePayLoad(newItem: RowType): Any? {
        return TrainingItemPayload(this, newItem as TrainingItem)
    }
}

data class TrainingItemPayload(
    val oldTrainingItem: TrainingItem,
    val newTrainingItem: TrainingItem
)