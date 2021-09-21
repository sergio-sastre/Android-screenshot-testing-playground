package com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation

class MemorisesInfoProvider(private val list: List<Memorise>) {

    fun getAllTranslations(): List<Translation> {
        return list.toMutableList().fold(emptyList<Translation>())
        { acc, translations -> acc + translations.translations }
            .toList()
    }

    fun getTranslationsToTrain(): List<Translation> {
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