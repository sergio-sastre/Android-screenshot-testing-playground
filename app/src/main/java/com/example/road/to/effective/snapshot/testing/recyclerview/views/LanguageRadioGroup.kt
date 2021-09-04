package com.example.road.to.effective.snapshot.testing.recyclerview.views

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.RadioGroup
import androidx.core.view.get
import com.example.road.to.effective.snapshot.testing.recyclerview.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.bubbleSortDescending
import java.util.*

class LanguageRadioGroup : RadioGroup {

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context) : super(context)

    @SuppressLint("ObjectAnimatorBinding")
    private fun addViewWithAnimation(
        view: LanguageFilterView,
        startAnimDelay: Long = 500,
        pos: Int = -1
    ) {

        val appear: Animator = ObjectAnimator.ofPropertyValuesHolder(
            null as Any?,
            PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        ).apply {
            duration = 500
            startDelay = startAnimDelay
            interpolator = OvershootInterpolator()
        }

        val itemLayoutTransition = layoutTransition
            .apply { setAnimator(LayoutTransition.APPEARING, appear) }
        layoutTransition = itemLayoutTransition

        addView(view, pos)
    }

    fun addNewLangs(
        context: Context,
        allLangsSorted: SortedSet<Language>,
        langsToAdd: Map<Language, Collection<Translation>>,
        activeLangs: Set<Language>,
        listener: LanguageFilterClickedListener? = null
    ) {
        langsToAdd.mapKeys { entry ->
            val amountTexts = langsToAdd[entry.key]?.size ?: 0
            val checkMark =
                LanguageFilterView.createLanguageRadioButtonLayout(
                    ctx = context,
                    checked = activeLangs.contains(entry.key),
                    viewTag = entry.key,
                    amountTexts = amountTexts,
                    listener = listener
                )

            val langPos = allLangsSorted.indexOf(entry.key)
            addViewWithAnimation(checkMark, pos = langPos)
        }
    }

    fun reorderLangs(
        oldLangsOrder: LinkedHashSet<Language>,
        newLangsOrder: LinkedHashSet<Language>
    ) {
        val newLangWeights = newLangsOrder
            .mapIndexed { index, language ->
                Pair(language, newLangsOrder.size - index)
            }.toMap()

        val oldLangWeights = oldLangsOrder
            .mapIndexed { _, language ->
                Pair(language, newLangWeights[language])
            }.toMap()

        oldLangWeights
            .values.filterNotNull().toIntArray()
            .bubbleSortDescending(doOnSwap = { initPos, finalPos ->
                val view = get(initPos) as LanguageFilterView
                removeViewAt(initPos)
                addViewWithAnimation(view, 0, finalPos)
            })
    }

    fun getLangRadioButtonByTag(langTag: Language): LanguageFilterView? =
        findViewWithTag<View>(langTag) as? LanguageFilterView?

    fun removeLangs(langsToRemove: Set<Language>) {
        langsToRemove.forEach { lang ->
            removeView(findViewWithTag<View>(lang))
        }
    }
}