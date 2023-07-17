package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.viewholder

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem

object MemoriseTestItemGenerator {
    /**
     * Important to pass the activity: when using [Context] methods, always pass the one of the
     * activity we have configured, and not that from InstrumentationRegistry.instrumentation!
     *
     * Otherwise, the UI might be affected. For instance in this method,
     * the string will not be correctly localized with the locale of the Activity
     */
    private fun generateMemorise(activity: Context, titleSuffix: String = ""): Memorise {
        val memoriseTitle = activity.getString(R.string.english_memorise_title)
        val titleText = if (titleSuffix.isBlank()) memoriseTitle else "$memoriseTitle $titleSuffix"
        return Memorise(
            id = 2,
            landmark = 4,
            srcLang = Language.English,
            title = titleText,
            text = activity.getString(R.string.english_memorise_body),
            translations = englishTransl.repeated(3)
        )
    }

    fun generateMemoriseItem(
        activity: Context,
        rightAligned: Boolean,
        titleSuffix: String = ""
    ) =
        MemoriseItem(
            memorise = generateMemorise(activity, titleSuffix),
            rightAligned = rightAligned,
            currentTime = 0L
        )

    private val englishTransl =
        Translation(
            "hallo",
            setOf(),
            Language.English,
            Language.English
        )

    private fun Translation.repeated(times: Int): List<Translation> =
        mutableListOf<Translation>().apply {
            repeat(times) { add(this@repeated) }
        }
}
