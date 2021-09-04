package com.example.road.to.effective.snapshot.testing.recyclerview.dataproviders

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation

class NonLoggedUserMemoriseProvider(private val context: Context) : MemoriseProvider {
    override fun getMemorises(): List<Memorise> {
        //TODO - this is just for testing
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

        //TODO - finished for Testing
        val sourceLang = Language.English
        val title = "Holy Moly"//context.getString(R.string.title_sample)
        val body = "Holy molly body"//context.getString(R.string.lorem_ipsum)
        return listOf(
            Memorise(
                id = 1,
                landmark = 4,
                srcLang = Language.Russian,
                title = title,
                text = body,
                translations = listOf(russianTransl, russianTransl)
            ),
            Memorise(
                id = 6,
                landmark = 4,
                srcLang = sourceLang,
                title = title,
                text = body,
                translations = listOf(englishTransl, englishTransl, englishTransl)
            ),
            Memorise(
                id = 2,
                landmark = 3,
                srcLang = Language.Russian,
                title = title,
                text = body,
                translations = listOf(russianTransl, russianTransl, russianTransl)
            ),

            Memorise(
                id = 3,
                landmark = 0,
                srcLang = Language.German,
                title = title,
                text = body,
                translations = listOf(germanTransl)
            )
        )

    }
}