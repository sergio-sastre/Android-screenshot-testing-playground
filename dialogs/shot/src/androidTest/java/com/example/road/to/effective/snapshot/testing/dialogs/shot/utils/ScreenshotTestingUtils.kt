package com.example.road.to.effective.snapshot.testing.dialogs.shot

import android.app.Dialog
import android.view.View
import sergio.sastre.uitesting.utils.utils.waitForView

/**
 * Waits for the Ui thread to be Idle and calculates the expected dimensions of the [Dialog].
 * This is necessary to take a screenshot with the expected size of the dialog. For that, we need
 * to provide [Dialog].measureHeight/measureWidth to the method used to take/verify the screenshot.
 */
fun waitForMeasuredDialog(actionToDo: () -> Dialog): Dialog {
    val dialog = waitForView { actionToDo() }
    dialog.window?.decorView!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return dialog
}
