package com.example.road.to.effective.snapshot.testing.recyclerview.rows.memorisetext

import android.content.Context
import android.os.Handler
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerview.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerview.utils.PopUpWindowBuilder
import com.google.android.material.card.MaterialCardView

class MemoriseViewHolder<T>(
    private val container: View,
    private val itemEventListener: T?
) : RecyclerView.ViewHolder(container) where T : DeleteMemoriseListener, T : MemoriseClickedListener {
    val mainLayout: MaterialCardView = container.findViewById(R.id.mainLayout)

    val appCompatImageButton: AppCompatImageButton =
        container.findViewById(R.id.appCompatImageButton)

    val textTitle: TextView = container.findViewById(R.id.title)

    val textBody : TextView =
        container.findViewById(R.id.body)

    private fun setupView(item: MemoriseItem){
        mainLayout.setOnClickListener {
            itemEventListener?.onMemoriseClicked(item.memorise)
        }

        appCompatImageButton.setOnClickListener {
            val rootView = container.findViewById<ViewGroup>(R.id.root)
            val layout: View = LayoutInflater.from(it.context).inflate(R.layout.memorise_overflow_layout, rootView, false)

            val dimension =
                it.context.resources.getDimension(R.dimen.popup_window_min_width).toInt()
            val popUp = PopUpWindowBuilder.buildActionItemWindow(layout, dimension)

            val translationsToTrain =
                item.memorise.translations.size

            layout.findViewById<ViewStub>(R.id.trainingLayout)
                ?.setTrainingActionItem(translationsToTrain) { popUp.dismiss() }

            layout.findViewById<View>(R.id.trainingLayout).run {
                isVisible = translationsToTrain > 0
            }

            layout.findViewById<TextView>(R.id.deleteButton).setOnClickListener {
                itemEventListener?.onMemoriseDeleted(item.memorise)
                popUp.dismiss()
            }

            popUp.showAsDropDownForItem(item, appCompatImageButton)
        }

        textTitle.text = item.memorise.title
        textBody.text = item.memorise.text
    }

    fun bind(item: MemoriseItem) {
        setupView(item)
        item.animate(container.context, container)
    }

    fun update(item: MemoriseItem){
        setupView(item)
        Handler().postDelayed( {
            item.animate(container.context, container)
        }, 500L)
    }

    private fun PopupWindow.showAsDropDownForItem(item: MemoriseItem, anchor: View) {
        val gravity = if (item.rightAligned) Gravity.START else Gravity.END
        this.showAsDropDown(anchor, 0, 0, gravity)
    }

    private fun ViewStub?.setTrainingActionItem(
        translationsToTrain: Int,
        onInflatedClicked: () -> Unit
    ) {
        this?.setOnInflateListener { _, inflated ->
            inflated.findViewById<TextView>(R.id.trainButton).text =
                inflated.context.resources.getQuantityString(
                    R.plurals.plural_train_words,
                    translationsToTrain,
                    translationsToTrain
                )
            inflated.setOnClickListener {
                onInflatedClicked()
            }
        }
    }

    private fun MemoriseItem.animate(context: Context, container: View) {
        if (rightAligned.not()) {
            animateToLayout(R.layout.memorise_row, context, container)
        } else {
            animateToLayout(R.layout.memorise_mirrored_row, context, container)
        }
    }

    private fun animateToLayout(
        @LayoutRes layoutRes: Int,
        context: Context,
        container: View
    ) {
        val contentLayout = container.findViewById<ConstraintLayout>(R.id.contentLayout)
        val root = container.findViewById<ConstraintLayout>(R.id.root)
        val constraint1 = ConstraintSet()
        constraint1.clone(contentLayout)

        val constraint2 = ConstraintSet()
        val view2 = LayoutInflater.from(context)
            .inflate(layoutRes, null, false)
        constraint2.clone(view2.findViewById(R.id.contentLayout) as ConstraintLayout)

        val constraint3 = ConstraintSet()
        constraint3.clone(view2.findViewById(R.id.root) as ConstraintLayout)

        constraint3.applyTo(root)
        constraint2.applyTo(contentLayout)

        TransitionManager.beginDelayedTransition(root)
        Handler().postDelayed(
            {
                TransitionManager.beginDelayedTransition(contentLayout)
            }, 500
        )
    }
}