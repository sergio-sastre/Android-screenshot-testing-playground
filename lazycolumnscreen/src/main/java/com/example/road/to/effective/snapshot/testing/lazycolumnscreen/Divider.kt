package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun AppDivider(
    padding: PaddingValues = PaddingValues()
) {
    HorizontalDivider(
        modifier = Modifier
            .padding(padding)
            .alpha(0.12f),
        color = if (isSystemInDarkTheme()) {
            Color.White
        } else {
            Color.Black
        }
    )
}
