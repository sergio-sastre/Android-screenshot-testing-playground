package com.example.road.to.effective.snapshot.testing

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton

object DialogBuilder {

    fun buildDeleteDialog(
        ctx: Context,
        onDeleteClicked: () -> Unit,
        onCancelClicked: () -> Unit = {},
        vararg bulletTexts: CharSequence
    ): Dialog {
        val dialogView: View =
            LayoutInflater.from(ctx).inflate(R.layout.delete_dialog_layout, null, false)
        return Dialog(ctx).apply {

            //Bullets
            val bulletGroup = dialogView.findViewById<ViewGroup>(R.id.bulletGroup)
            bulletTexts.map {
                val newBullet =
                    LayoutInflater.from(ctx).inflate(R.layout.bullet_text, bulletGroup, false)
                        .apply {
                            findViewById<TextView>(R.id.bullet).text = it
                        }
                bulletGroup.addView(newBullet)
            }

            //Dialog
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogView.findViewById<ConstraintLayout>(R.id.root).setOnClickListener {
                dismiss()
            }
            dialogView.findViewById<MaterialButton>(R.id.deleteButton).setOnClickListener {
                dismiss()
                onDeleteClicked()
            }
            dialogView.findViewById<MaterialButton>(R.id.cancelButton).setOnClickListener {
                dismiss()
                onCancelClicked()
            }
            dialogView.findViewById<View>(R.id.root).setOnClickListener { dismiss() }
            setContentView(dialogView)
        }
    }

}