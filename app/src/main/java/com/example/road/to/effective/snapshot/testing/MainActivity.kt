package com.example.road.to.effective.snapshot.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.rootView.run {
            findViewById<MaterialButton>(R.id.showDialogButton)
                .setOnClickListener { showDeleteDialog() }

            findViewById<MaterialButton>(R.id.showRecyclerViewButton)
                .setOnClickListener { showRecyclerView() }

            findViewById<MaterialButton>(R.id.showComposeActivityButton)
                .setOnClickListener { showComposeActivity() }
        }
    }

    private fun showDeleteDialog() {
        DialogBuilder.buildDeleteDialog(
            this,
            onDeleteClicked = {/* no-op*/ },
            bulletTexts = arrayOf(
                "Item one, undoubtedly the longest",
                "Item two, also very long",
                "Item three, short"
            )
        ).show()
    }

    private fun showRecyclerView() {
        startActivity(Actions.openRecyclerView(this))
    }

    private fun showComposeActivity() {
        startActivity(Actions.openCoffeeDrinks(this))
    }
}