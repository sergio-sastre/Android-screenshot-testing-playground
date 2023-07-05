package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R

class LanguageTrainingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LanguageTrainingFragment.newInstance())
                .commitNow()
        }
    }
}