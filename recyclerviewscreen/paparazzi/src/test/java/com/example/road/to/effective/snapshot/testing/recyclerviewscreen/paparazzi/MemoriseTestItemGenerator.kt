package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi

import android.content.Context
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem

object MemoriseTestItemGenerator {
    /**
     * Important to pass the activity: when using [Context] methods, always pass the one of the
     * activity we have configured, and not that from InstrumentationRegistry.instrumentation!
     *
     * Otherwise, the UI might be affected. For instance in this method,
     * the string will not be correctly localized with the locale of the Activity
     */
    private fun generateMemorise(activity: Context) =
        com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise(
            id = 2,
            landmark = 4,
            srcLang = com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English,
            title = activity.getString(R.string.english_memorise_title),
            text = activity.getString(R.string.english_memorise_body),
            translations = englishTransl.repeated(3)
        )

    fun generateMemoriseItem(
        activity: Context,
        rightAligned: Boolean
    ) =
        MemoriseItem(
            memorise = generateMemorise(activity),
            rightAligned = rightAligned,
            currentTime = 0L
        )

    private val englishTransl =
        com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation(
            "hallo",
            setOf(),
            com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English,
            com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language.English
        )

    private fun com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation.repeated(times: Int): List<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation> =
        mutableListOf<com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation>().apply {
            repeat(times) { add(this@repeated) }
        }
}
