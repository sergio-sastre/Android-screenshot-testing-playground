package com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders.memorise

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation

class UserMemoriseProvider(private val context: Context) : MemoriseProvider {
    override fun getMemorises(): List<Memorise> {
        // Mock data
        val englishTransl =
            Translation(
                "hallo",
                setOf(),
                Language.English,
                Language.English
            )

        val russianTransl = Translation(
            "hallo",
            setOf(),
            Language.Russian,
            Language.Russian
        )

        val germanTransl = Translation(
            "hallo",
            setOf(),
            Language.German,
            Language.German
        )

        return listOf(
            Memorise(
                id = 1,
                landmark = 4,
                srcLang = Language.Russian,
                title = context.getString(R.string.russian_memorise_title),
                text = context.getString(R.string.russian_memorise_body),
                translations = russianTransl.repeated(5)
            ),
            Memorise(
                id = 2,
                landmark = 4,
                srcLang = Language.English,
                title = context.getString(R.string.english_memorise_title),
                text = context.getString(R.string.english_memorise_body),
                translations = englishTransl.repeated(3)
            ),
            Memorise(
                id = 3,
                landmark = 0,
                srcLang = Language.German,
                title = context.getString(R.string.german_memorise_title),
                text = context.getString(R.string.german_memorise_body),
                translations = germanTransl.repeated(1)
            )
        )

    }

    private fun Translation.repeated(times: Int): List<Translation> =
        mutableListOf<Translation>().apply {
            repeat(times) { add(this@repeated) }
        }
}