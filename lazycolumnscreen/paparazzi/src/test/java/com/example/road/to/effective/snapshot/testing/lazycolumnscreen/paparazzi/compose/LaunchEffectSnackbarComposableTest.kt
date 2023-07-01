package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.compose

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi.utils.SnackbarScaffold
import org.junit.Rule
import org.junit.Test

/**
 * Execute the command below to run only ComposableTests
 * 1. Record:
 *    ./gradlew :lazycolumnscreen:paparazzi:recordPaparazziDebug --tests '*Composable*'
 * 2. Verify:
 *    ./gradlew :lazycolumnscreen:paparazzi:verifyPaparazziDebug --tests '*Composable*'
 */
class SnackbarComposableTest {

    @get:Rule
    val paparazzi =
        Paparazzi(deviceConfig = DeviceConfig.PIXEL_5.copy(softButtons = false))

    @Test
    fun snapComposable() {
        paparazzi.snapshot(name = "ActionNotSupportedSnackbar") {
            AppTheme {
                SnackbarScaffold { snackbarHostState ->
                    ActionNotSupportedSnackbar(
                        snackbarHostState = snackbarHostState,
                        onDismiss = {}
                    )
                }
            }
        }
    }
}
