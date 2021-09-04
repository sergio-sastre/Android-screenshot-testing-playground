package com.example.road.to.effective.snapshot.testing.utils

import com.example.road.to.effective.snapshot.testing.R

enum class DialogTheme(val themId: Int) {
    MATERIAL_DARK_DIALOG(R.style.Theme_MaterialComponents_Dialog),
    MATERIAL_LIGHT_DIALOG(R.style.Theme_MaterialComponents_Light_Dialog),
    MATERIAL_DARK(R.style.Theme_MaterialComponents),
    MATERIAL_LIGHT(R.style.Theme_MaterialComponents_Light);
}
