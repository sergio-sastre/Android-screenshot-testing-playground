package com.example.road.to.effective.snapshot.testing.recyclerview.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox

class LanguageRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCheckBox(context, attrs, defStyleAttr) {

    var animationDuration = ANIMATION_DURATION

    companion object {
        const val CHECKED_ALPHA = 1f
        const val UNCHECKED_ALPHA = 0.5f
        const val ANIMATION_DURATION = 300L
    }

    fun setAnimationOn(){
        animationDuration = ANIMATION_DURATION
    }

    fun setAnimationOff(){
        animationDuration = 0L
    }

    override fun setChecked(checked: Boolean) {
        if (!checked) {
            animate()
                .scaleX(0.85f)
                .scaleY(0.85f)
                .alpha(UNCHECKED_ALPHA)
                .duration = animationDuration

        } else {
            animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(CHECKED_ALPHA)
                .duration = animationDuration
        }
        super.setChecked(checked)
    }
}
