package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data

data class Translation(
    val word: String,
    val wordRanges: Set<IntRange>,
    val srcLang: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language = com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English,
    val destLang: com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language = com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English,
)