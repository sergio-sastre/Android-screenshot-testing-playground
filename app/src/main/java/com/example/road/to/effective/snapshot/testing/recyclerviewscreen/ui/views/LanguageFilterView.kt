package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Checkable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.LanguageFilterClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.getCountryFlag

class LanguageFilterView : ConstraintLayout, Checkable {
    lateinit var filterText: DigitTextView
    lateinit var radioButton: LanguageRadioButton

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    companion object {
        fun createLanguageRadioButtonLayout(
            ctx: Context,
            checked: Boolean,
            viewTag: Language,
            amountTexts: Int,
            listener: LanguageFilterClickedListener? = null,
        ): LanguageFilterView {

            return LanguageFilterView(ctx).apply {
                setLanguage(viewTag)
                setValue(amountTexts)
                radioButton.setAnimationOff()
                isChecked = checked
                radioButton.setAnimationOn()
                setLanguageFilterCheckedListener(listener)
            }

        }
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.language_filter_layout, this)
        filterText = rootView.findViewById(R.id.filter_amount_texts)
        radioButton = rootView.findViewById<LanguageRadioButton>(R.id.filter).apply {
            layoutParams = LayoutParams(context, null)
        }
    }

    fun setValue(value: Int) {
        filterText.run {
            longTransformer = LongAsShortTransformer(context)
            setValue(value.toLong())
        }
    }

    fun setLanguage(language: Language) {
        tag = language
        radioButton.setBackgroundResource(language.getCountryFlag())
    }

    fun setLanguageFilterCheckedListener(listener: LanguageFilterClickedListener?) {
        radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            (tag as? Language?)?.run {
                listener?.onLanguageFilterClicked(this)
            }
            filterText.isChecked = isChecked
        }
    }

    override fun setChecked(checked: Boolean) {
        radioButton.isChecked = checked
        filterText.isChecked = checked
    }

    override fun isChecked(): Boolean {
        return radioButton.isChecked && filterText.isChecked
    }

    override fun toggle() {
        radioButton.toggle()
        filterText.toggle()
    }
}