package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation

class MemorisesInfoCollector(private val list: List<Memorise>) {

    private fun getAllTranslations(): List<Translation> {
        return list.toMutableList().fold(emptyList<Translation>())
        { acc, translations -> acc + translations.translations }
            .toList()
    }

    private fun getTranslationsToTrain(): List<Translation> {
        return list.toMutableList().fold(emptyList<Translation>())
        { acc, translations -> acc + translations.translations }
            .toList()
    }

    fun getTranslationsByDestLang(): Map<Language, List<Translation>> {
        return getAllTranslations().groupBy { it.destLang }
    }

    fun getTranslationsToTrainByDestLang(): Map<Language, List<Translation>> {
        return getTranslationsToTrain().groupBy { it.destLang }
    }

}