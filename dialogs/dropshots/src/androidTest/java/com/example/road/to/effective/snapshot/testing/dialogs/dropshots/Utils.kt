package com.example.road.to.effective.snapshot.testing.dialogs.dropshots

import android.app.Dialog
import android.view.View
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import sergio.sastre.uitesting.utils.utils.waitForView

/**
 * Waits for the Ui thread to be Idle and calculates the expected dimensions of the [Dialog].
 * This is necessary to take a screenshot with the expected size of the dialog. For that, we need
 * to provide [Dialog].measureHeight/measureWidth to the method used to take/verify the screenshot.
 */
fun waitForDialogView(actionToDo: () -> Dialog): View {
    val dialog = waitForView { actionToDo().apply { show() } }
    dialog.view().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    getInstrumentation().run {
        runOnMainSync { dialog.view().requestLayout() }
        waitForIdleSync()
    }
    return dialog.view()
}

private fun Dialog.view() = this.window!!.decorView