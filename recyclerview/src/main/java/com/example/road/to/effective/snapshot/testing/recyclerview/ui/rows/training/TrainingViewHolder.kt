package com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.TrainAllClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.sortByValueSize
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.views.DigitTextView
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.views.LanguageRadioGroup
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.views.LongAsShortTransformer

class TrainingViewHolder(
    private val container: View,
) : RecyclerView.ViewHolder(container) {
    val contextWrapper =
        ContextThemeWrapper(container.context, R.style.FlagCheckMark)

    private var filterClickedListener: LanguageFilterClickedListener? = null
    private var trainAllClickedListener: TrainAllClickedListener? = null

    fun <T> bind(
        item: TrainingItem,
        languageClickedListener: T?,

        ) where T : TrainAllClickedListener,
                T : LanguageFilterClickedListener {

        filterClickedListener = languageClickedListener
        trainAllClickedListener = languageClickedListener

        container.findViewById<LanguageRadioGroup>(R.id.radioGroup)
            .bindTrainingItem(contextWrapper, item, languageClickedListener)

        with(container.findViewById<DigitTextView>(R.id.amountText)) {
            longTransformer = LongAsShortTransformer(container.context)
            setAnimationOff()
            setValue(item.getWordsToMemoriseAmount())
            setAnimationOn()
        }

        container.animateEmptyStateTransition(item.trainingByLang.isEmpty())

        with(container.findViewById<Button>(R.id.trainButton)) {
            setOnClickListener { trainAllClickedListener?.onTrainAllClicked() }
            isEnabled = item.trainingByLang.isNotEmpty()
            alpha = if (isEnabled) 1f else 0.5f
        }
    }

    fun update(payload: TrainingItemPayload) {
        val oldLangsSorted = payload.oldTrainingItem.trainingByLang.sortByValueSize()
        val newLangsSorted = payload.newTrainingItem.trainingByLang.sortByValueSize()

        container.animateEmptyStateTransition(newLangsSorted.isEmpty())

        container.findViewById<LanguageRadioGroup>(R.id.radioGroup)
            .animateLanguageFilters(
                contextWrapper,
                payload.newTrainingItem,
                filterClickedListener,
                oldLangsSorted,
                newLangsSorted,
            )

        with(container.findViewById<Button>(R.id.trainButton)) {
            isEnabled = payload.newTrainingItem.activeLangs.isNotEmpty()
            alpha = if (isEnabled) 1f else 0.5f
        }

        with(container.findViewById<DigitTextView>(R.id.amountText)) {
            longTransformer = LongAsShortTransformer(context)
            setValue(payload.newTrainingItem.getWordsToMemoriseAmount())
        }
    }

    private fun View.animateEmptyStateTransition(
        showEmptyState: Boolean
    ) {
        val fadingInDuration = 600L
        val fadingOutDuration = 1_000L
        val invisibleAlpha = 0f
        val visibleAlpha = 1f

        val filterText = findViewById<TextView>(R.id.filterText)
        val emptyMemorisesImage = findViewById<ImageView>(R.id.emptyMemorisesImage)

        when {
            showEmptyState -> {
                filterText.animate().setDuration(fadingOutDuration).alpha(invisibleAlpha)
                emptyMemorisesImage.animate().setDuration(fadingInDuration).alpha(visibleAlpha)
            }
            else -> {
                filterText.animate().setDuration(fadingInDuration).alpha(visibleAlpha)
                emptyMemorisesImage.animate().setDuration(fadingOutDuration)
                    .alpha(invisibleAlpha)
            }
        }
    }

    private fun TrainingItem.getWordsToMemoriseAmount(): Long =
        trainingByLang
            .filterKeys { activeLangs.contains(it) }
            .values.fold(emptyList<Translation>()) { acc, list -> acc + list }
            .size
            .toLong()
}

