package com.example.road.to.effective.snapshot.testing.utils

import android.app.Activity
import androidx.annotation.StyleRes
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest

fun <V>ScreenshotTest.waitForView(actionToDo: () -> V): V =
    getInstrumentation().run {
        var view: V? = null
        runOnMainSync { view = actionToDo() }
        waitForIdleSync()
        return view!!
    }

fun <A : Activity> ActivityScenario<A>.waitForActivity(@StyleRes theme: Int): Activity =
    this.waitForActivity().apply { setTheme(theme) }
