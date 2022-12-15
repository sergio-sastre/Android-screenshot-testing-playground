package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation

class MemorisesInfoCollector(private val list: List<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise>) {

    private fun getTranslationsToTrain(): List<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation> {
        return list.toMutableList().fold(emptyList<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation>())
        { acc, translations -> acc + translations.translations }
            .toList()
    }

    fun getTranslationsToTrainByDestLang(): Map<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language, List<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation>> {
        return getTranslationsToTrain().groupBy { it.destLang }
    }

}