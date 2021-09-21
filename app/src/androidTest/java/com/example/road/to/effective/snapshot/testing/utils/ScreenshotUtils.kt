package com.example.road.to.effective.snapshot.testing.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest

/**
 * Setup the view under test and wait till the view is ready and main thread is idle
 * before recording any snapshot
 *
 * @param actionToDo: Everything that drives the view to the state we want to snapshot.
 * This also includes inflation
 */
fun <V>ScreenshotTest.waitForView(actionToDo: () -> V): V =
    getInstrumentation().run {
        var view: V? = null
        runOnMainSync { view = actionToDo() }
        waitForIdleSync()
        return view!!
    }

fun <A : Activity> ActivityScenario<A>.waitForActivity(@StyleRes theme: Int): Activity =
    this.waitForActivity().apply { setTheme(theme) }

fun Activity.inflate(@LayoutRes layoutId: Int): View {
    val root = findViewById<View>(android.R.id.content) as ViewGroup
    return LayoutInflater.from(this)
        .inflate(layoutId, root, true)
}
