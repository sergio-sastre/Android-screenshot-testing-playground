package com.example.road.to.effective.snapshot.testing.parameterized.trainingitem

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import com.example.road.to.effective.snapshot.testing.utils.config.ViewWidth
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

data class TrainingTestItem(
    val locale: String = "en",
    val fontScale: FontSize = FontSize.NORMAL,
    val uiMode: UiMode = UiMode.DAY,
    val viewWidth: ViewWidth = ViewWidth.DEVICE_WIDTH,
    val trainingItem: TrainingItem = TrainingItem(
        trainingByLang = mapOf(),
        activeLangs = emptySet()
    ),
    val testName: String,
)
