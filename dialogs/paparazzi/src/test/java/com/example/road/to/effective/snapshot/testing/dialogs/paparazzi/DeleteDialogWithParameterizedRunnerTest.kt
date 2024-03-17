package com.example.road.to.effective.snapshot.testing.dialogs.paparazzi

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Execute the command below to run only DialogTests
 * 1. Record:
 *    ./gradlew :dialogs:paparazzi:recordPaparazziDebug --tests '*Dialog*'
 * 2. Verify:
 *    ./gradlew :dialogs:paparazzi:verifyPaparazziDebug --tests '*Dialog*'
 */

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 */
@RunWith(Parameterized::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> =
            HappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
        // Needed to avoid crashes due to compileSdk 34
        environment = detectEnvironment().copy(
            platformDir = "${androidHome()}/platforms/android-33",
            compileSdkVersion = 33,
        ),
        renderingMode = RenderingMode.SHRINK,
    )

    @Test
    fun snapDialogHappyPath() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )

        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> =
            UnhappyPathTestItem.entries.toTypedArray()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
        renderingMode = RenderingMode.SHRINK,
    )

    @Test
    fun snapDialogUnhappyPath() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )
        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}