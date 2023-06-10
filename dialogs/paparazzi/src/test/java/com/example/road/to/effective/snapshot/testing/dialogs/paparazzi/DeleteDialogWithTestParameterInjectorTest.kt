package com.example.road.to.effective.snapshot.testing.dialogs.paparazzi

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:paparazzi:recordPaparazziDebug --tests '*Dialog*'
 * 2. Verify:
 *    ./gradlew :dialogs:paparazzi:verifyPaparazziDebug --tests '*Dialog*'
 */

/**
 * Example of Parameterized test with TestParameterInjector Runner.
 *
 * Unlike Parameterized Runner, the test methods admit arguments, although we do not use them here.
 */
@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterHappyPathTest(
    @TestParameter val testItem: HappyPathTestItem,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5.copy(
            softButtons = false,
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
    )

    @Test
    fun snapDialog() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )
        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}

@RunWith(TestParameterInjector::class)
class DeleteDialogTestParameterUnhappyPathTest(
    @TestParameter val testItem: UnhappyPathTestItem,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5.copy(
            softButtons = false,
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
    )

    @Test
    fun snapDialog() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )
        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_TestParameter",
        )
    }
}