package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.utils.ConfigTestItem
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode

object RecyclerViewActivityObjectMother {

    fun happyPath() =
        ConfigTestItem(
            uiMode = UiMode.DAY,
            fontSize = FontSize.NORMAL,
            locale = "en",
            orientation = Orientation.PORTRAIT,
            name = "RecyclerViewActivity_Portrait_HappyPath"
        )

    fun unhappyPath() =
        ConfigTestItem(
            uiMode = UiMode.NIGHT,
            fontSize = FontSize.HUGE,
            locale = "ar",
            orientation = Orientation.PORTRAIT,
            name = "RecyclerViewActivity_Portrait_UnhappyPath"
        )
}
