package com.example.road.to.effective.snapshot.testing.recyclerview.data

data class Translation(
    val word: String,
    val wordRanges: Set<IntRange>,
    val srcLang: Language = Language.English,
    val destLang: Language = Language.English,
)