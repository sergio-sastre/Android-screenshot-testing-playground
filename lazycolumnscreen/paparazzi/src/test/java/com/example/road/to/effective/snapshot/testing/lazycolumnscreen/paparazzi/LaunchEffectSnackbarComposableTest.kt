package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.paparazzi

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
 *
 * WARNING: Paparazzi cannot render the Snackbar.
 *          That is because it relies on layoutlib (used in Compose Previews), and previews cannot render code in LaunchEffects
 */
class SnackbarComposableTest {

    @get:Rule
    val paparazzi =
        Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

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
