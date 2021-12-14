package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.road.to.effective.snapshot.testing.R

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecyclerViewFragment.newInstance())
                .commitNow()
        }
    }
}