package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.views

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Checkable
import android.widget.FrameLayout
import android.widget.TextView
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R

class DigitTextView : FrameLayout, Checkable {
    lateinit var currentTextView: TextView
    lateinit var nextTextView: TextView
    private var currentValue = 0L
    private var isChecked = true
    private var animationDuration = ANIMATION_DURATION

    var longTransformer: LongTransformer =
        NoTransformer(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    interface LongTransformer {
        fun convertToString(long: Long): String
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.digit_text_view, this)
        currentTextView = rootView.findViewById(R.id.currentTextView)
        nextTextView = rootView.findViewById<TextView>(R.id.nextTextView).apply {
            translationY = height.toFloat()
        }
        setValue(currentValue)
    }

    fun setAnimationOn() {
        animationDuration = ANIMATION_DURATION
    }

    fun setAnimationOff() {
        animationDuration = 0L
    }

    fun setValue(desiredValue: Long) {
        val oldValue = currentValue
        currentValue = desiredValue

        val oldValueAsText = longTransformer.convertToString(oldValue)
        val desiredValueAsText = longTransformer.convertToString(desiredValue)

        if (oldValueAsText != desiredValueAsText || currentTextView.text != oldValueAsText) {
            nextTextView.text = desiredValueAsText

            val isDecreasing = oldValue > desiredValue
            val currentTranslationYFactor = if (isDecreasing) -1f else 1f
            val newTranslationYFactor = -1f * currentTranslationYFactor

            currentTextView.animate().translationY(currentTranslationYFactor * height)
                .setDuration(animationDuration)
                .start()

            nextTextView.apply {
                translationY = newTranslationYFactor * height
                animate().translationY(0f).setDuration(animationDuration)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            currentTextView.apply {
                                text = desiredValueAsText
                                translationY = 0f
                            }
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    }).start()
            }
        }
    }


    companion object {
        private const val ANIMATION_DURATION = 300L
    }

    override fun setChecked(checked: Boolean) {
        if (checked == isChecked) return

        if (!checked) {
            animate()
                .scaleX(0.85f)
                .scaleY(0.85f)
                .alpha(LanguageRadioButton.UNCHECKED_ALPHA)
                .duration = animationDuration
        } else {
            animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(LanguageRadioButton.CHECKED_ALPHA)
                .duration = animationDuration
        }
        isChecked = checked
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        setChecked(!isChecked)
    }
}