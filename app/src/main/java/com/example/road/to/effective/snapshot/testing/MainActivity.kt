package com.example.road.to.effective.snapshot.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.rootView.findViewById<MaterialButton>(R.id.showDialogButton)
            .setOnClickListener { openDialog() }
    }

    private fun openDialog() {
        val dialog = DialogBuilder.buildDeleteDialog(
            this,
            onDeleteClicked = {/* no-op*/ },
            bulletTexts = arrayOf(
                "Item one, undoubtedly the longest",
                "Item two, also very long",
                "Item three, short"
            )
        )
        dialog.show()
    }
}