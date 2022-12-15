package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English

data class Memorise(
    val id: Int,
    val srcLang: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language = English,
    val destLang: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language = English,
    val translations: List<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation> = emptyList(),
    val title: String = "",
    val text: String = "",
    val landmark: Int = 0
)