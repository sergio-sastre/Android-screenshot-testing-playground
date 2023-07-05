package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_XL
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Memorise
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.dataproviders.memorise.UserMemoriseProvider
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewAsyncDiffAdapter
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RecyclerViewDiffUtilCallback
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.header.HeaderType.TRAINING
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingDelegate
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingItem
import org.junit.Rule
import org.junit.Test

class RecyclerViewTest {

    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = PIXEL_XL.copy(softButtons = false),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapFullRecyclerView() {
        // prepare the Items
        val memoriseItems: List<MemoriseItem> =
            UserMemoriseProvider(paparazzi.context).getMemorises().toMemoriseItems()

        val recyclerViewItems =
            listOf(HeaderItem(TRAINING), wordsInSomeLangsTrainingItem) + memoriseItems

        // prepare the Adapter
        val rvAdapter =
            RecyclerViewAsyncDiffAdapter(
                RecyclerViewDiffUtilCallback(),
                HeaderDelegate(),
                TrainingDelegate(null),
                MemoriseDelegate(null)
            )

        // Prepare the RecyclerView
        val constraintLayout: View = paparazzi.inflate(R.layout.recycler_view_fragment)
        (constraintLayout.findViewById<View>(R.id.memoriseList) as RecyclerView)
            .apply { adapter = rvAdapter }
            .apply { rvAdapter.items = recyclerViewItems }

        paparazzi.snapshot(
            name = "RecyclerView",
            offsetMillis = 3_000L,
            view = constraintLayout,
        )
    }
}

private val wordsInSomeLangsTrainingItem = TrainingItem(
    trainingByLang = mapOf(
        Language.English to translations(3),
        Language.Russian to translations(5),
        Language.German to translations(1)
    ),
    activeLangs = setOf(Language.Russian, Language.German)
)

private fun translations(amount: Int): List<Translation> {
    val translation = translation()
    return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
}

private fun translation(): Translation =
    Translation(
        "word",
        setOf(IntRange(0, 4)),
        Language.English,
        Language.English
    )

private fun List<Memorise>.toMemoriseItems(): List<MemoriseItem> =
    mapIndexed { index, memorise ->
        MemoriseItem(
            memorise = memorise,
            rightAligned = (index % 2 != 0),
            currentTime = 0,
        )
    }
