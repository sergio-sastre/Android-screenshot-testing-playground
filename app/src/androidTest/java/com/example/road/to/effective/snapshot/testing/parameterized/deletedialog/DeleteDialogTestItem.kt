package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog

import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

data class DeleteDialogTestItem(
    val fontScale: FontSize,
    val uiMode: UiMode,
    val texts: Array<Int>,
    val dialogWidth: DialogWidth,
    val testName: String
)
