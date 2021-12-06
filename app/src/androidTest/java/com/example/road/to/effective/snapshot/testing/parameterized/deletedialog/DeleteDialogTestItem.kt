package com.example.road.to.effective.snapshot.testing.parameterized.deletedialog

import com.example.road.to.effective.snapshot.testing.utils.config.DialogTheme
import com.example.road.to.effective.snapshot.testing.utils.config.DialogWidth
import sergio.sastre.fontsize.FontScale

data class DeleteDialogTestItem(
    val fontScale: FontScale,
    val theme: DialogTheme,
    val texts: Array<Int>,
    val dialogWidth: DialogWidth,
    val testName: String
)
