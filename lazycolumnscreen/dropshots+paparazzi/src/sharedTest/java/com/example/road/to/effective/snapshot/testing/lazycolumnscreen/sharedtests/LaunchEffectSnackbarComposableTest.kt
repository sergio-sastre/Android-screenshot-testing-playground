package com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests

import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.ActionNotSupportedSnackbar
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.AppTheme
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.SnackbarScaffold
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.sharedtests.utils.defaultCrossLibraryScreenshotTestRule
import com.example.road.to.effective.snapshot.testing.testannotations.ComposableTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.mapper.paparazzi.PaparazziConfig
import sergio.sastre.uitesting.mapper.paparazzi.wrapper.RenderingMode
import sergio.sastre.uitesting.utils.crosslibrary.config.ScreenshotConfigForComposable
import sergio.sastre.uitesting.utils.crosslibrary.runners.CrossLibraryScreenshotTestRunner

/**
 * You can execute these tests from the command line with different screenshot testing libraries as follows:
 * 1. Record task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:dropshots+paparazzi:recordPaparazziDebug
 *  2. Dropshots: ./gradlew :lazycolumnscreen:dropshots+paparazzi:connectedAndroidTest -Pdropshots.record
 *
 * 2. Verify task:
 *  1. Paparazzi: ./gradlew :lazycolumnscreen:dropshots+paparazzi:verifyPaparazziDebug
 *  2. Dropshots: ./gradlew :lazycolumnscreen:dropshots+paparazzi:connectedAndroidTest
 */
@RunWith(CrossLibraryScreenshotTestRunner::class)
class SnackbarComposableTest {

    @get:Rule
    val screenshotRule =
        defaultCrossLibraryScreenshotTestRule(ScreenshotConfigForComposable())
            .configure(PaparazziConfig(renderingMode = RenderingMode.NORMAL))

    @ComposableTest
    @Test
    fun snapComposable() {
        screenshotRule.snapshot(name = "ActionNotSupportedSnackbar") {
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
