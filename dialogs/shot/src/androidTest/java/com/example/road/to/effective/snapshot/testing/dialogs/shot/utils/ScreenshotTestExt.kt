package com.example.road.to.effective.snapshot.testing.dialogs.shot.utils

import android.app.Dialog
import android.os.Build
import com.karumi.shot.ScreenshotTest
import org.lsposed.hiddenapibypass.HiddenApiBypass

/**
 * Takes/verifies a screenshot of a dialog without the need to previously enable
 * non-SDK interfaces via adb.
 *
 * [ScreenshotTest.compareScreenshot] for [Dialog] uses a non-SDK interface.
 * If the non-SDK interface is not enabled before taking/verifying screenshots, it's skipped.
 * If you prefer to use the [ScreenshotTest.compareScreenshot] provided by Shot to this one, do not
 * forget to enable non-SDK interfaces via adb as stated in the documentation
 */
fun ScreenshotTest.compareDialogScreenshot(
    dialog: Dialog,
    heightInPx: Int? = dialog.window!!.decorView.height,
    widthInPx: Int? = dialog.window!!.decorView.width,
    name: String? = null
) {
    bypassNonSDKInterface {
        compareScreenshot(
            dialog = dialog,
            heightInPx = heightInPx,
            widthInPx = widthInPx,
            name = name,
        )
    }
}

private fun ScreenshotTest.bypassNonSDKInterface(actionToDo: ScreenshotTest.() -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        HiddenApiBypass.addHiddenApiExemptions("")
    }
    actionToDo()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        HiddenApiBypass.clearHiddenApiExemptions()
    }
}