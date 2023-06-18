package com.example.road.to.effective.snapshot.testing.dialogs.paparazzi.accessibility

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.R
import com.example.road.to.effective.snapshot.testing.dialogs.paparazzi.itemArray
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only AccessibilityTests
 * 1. Record:
 *    ./gradlew :dialogs:paparazzi:recordPaparazziDebug --tests '*Accessibility*'
 * 2. Verify:
 *    ./gradlew :dialogs:paparazzi:verifyPaparazziDebug --tests '*Accessibility*'
 */
class AccessibilityTest {

    @get:Rule
    val paparazzi = Paparazzi(
        // For Accessibility, better to use devices in landscape with Paparazzi
        deviceConfig = DeviceConfig.NEXUS_5_LAND.copy(softButtons = false),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
        renderExtensions = setOf(AccessibilityRenderExtension())
    )

    @Test
    fun snapDialogWithAccessibility() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, listOf(R.string.largest))
            )

        paparazzi.snapshot(
            view = dialog.window!!.decorView,
            name = "DeleteDialog_Accessibility",
        )
    }
}
