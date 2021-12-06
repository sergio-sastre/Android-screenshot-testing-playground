package com.example.road.to.effective.snapshot.testing.parameterized.trainingitem

import com.example.road.to.effective.snapshot.testing.recyclerview.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import sergio.sastre.fontsize.FontScale

data class TrainingTestItem(
    val fontScale: FontScale,
    val viewWidth: ViewWidth,
    val trainingItem: TrainingItem,
    val testName: String
)
