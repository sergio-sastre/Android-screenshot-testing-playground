package com.example.road.to.effective.snapshot.testing.dialogs.dropshots

import android.app.Dialog
import android.view.View
import sergio.sastre.uitesting.utils.utils.waitForView

/**
 * Waits for the Ui thread to be Idle and returns the [Dialog]'s window decorView.
 */
fun waitForDialogView(actionToDo: () -> Dialog): View {
    val dialog = waitForView { actionToDo().apply { show() } }
    return dialog.view()
}

private fun Dialog.view() = this.window!!.decorView