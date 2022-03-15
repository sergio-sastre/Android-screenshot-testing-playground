package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext

import android.content.Context
import android.os.Handler
import android.view.*
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.MAX_WORDS
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.PopUpWindowBuilder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.mainLocale
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.setFlagImage
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.setLandmarkImage
import com.google.android.material.card.MaterialCardView
import java.text.NumberFormat

class MemoriseViewHolder<T>(
    private val container: View,
    private val itemEventListener: T?,
    private val animationDelay: Long = 500L
) : RecyclerView.ViewHolder(container) where T : DeleteMemoriseListener, T : MemoriseClickedListener {
    val mainLayout: MaterialCardView = container.findViewById(R.id.mainLayout)

    val appCompatImageButton: AppCompatImageButton =
        container.findViewById(R.id.appCompatImageButton)

    val textTitle: TextView = container.findViewById(R.id.title)

    val textBody: TextView =
        container.findViewById(R.id.body)

    val sourceLangImage: AppCompatImageView = container.findViewById(R.id.sourceLangImage)

    val landmarkImage: AppCompatImageView = container.findViewById(R.id.imageView)

    val textInfo: TextView = container.findViewById(R.id.textInfo)
    val trainingInfo: TextView = container.findViewById(R.id.trainingInfo)
    val numberFormat = NumberFormat.getInstance(container.context.mainLocale())

    fun setupView(item: MemoriseItem) {
        mainLayout.setOnClickListener {
            itemEventListener?.onMemoriseClicked(item.memorise)
        }

        sourceLangImage.setFlagImage(item.memorise.srcLang)
        landmarkImage.setLandmarkImage(item.memorise.landmark, item.memorise.srcLang)
        textInfo.text = "${numberFormat.format(item.memorise.text.length)}/${numberFormat.format(MAX_WORDS)}"
        trainingInfo.text = numberFormat.format(item.memorise.translations.size)

        appCompatImageButton.setOnClickListener {
            val rootView = container.findViewById<ViewGroup>(R.id.root)
            val layout: View = LayoutInflater.from(it.context)
                .inflate(R.layout.memorise_overflow_layout, rootView, false)

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

    fun update(item: MemoriseItem) {
        setupView(item)
        Handler().postDelayed({
            item.animate(container.context, container)
        }, animationDelay)
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

        val newView = LayoutInflater.from(context)
            .inflate(layoutRes, null, false)

        val constraintContent = ConstraintSet().apply {
            clone(newView.findViewById(R.id.contentLayout) as ConstraintLayout)
        }

        val constraintRoot = ConstraintSet().apply {
            clone(newView.findViewById(R.id.root) as ConstraintLayout)
        }

        constraintRoot.applyTo(root)
        constraintContent.applyTo(contentLayout)

        TransitionManager.beginDelayedTransition(root)
        Handler().postDelayed(
            {
                TransitionManager.beginDelayedTransition(contentLayout)
            }, animationDelay
        )
    }
}