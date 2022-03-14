package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

object RecyclerViewActivityObjectMother {

    fun withUiMode(uiMode: UiMode) =
        ConfigTestItem(
            uiMode = uiMode,
            fontSize = FontSize.NORMAL,
            locale = "en",
            orientation = Orientation.PORTRAIT,
            name = "RecyclerViewActivity_${uiMode.name}"
        )
}
