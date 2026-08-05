package snapshot.testing.recyclerview_previews.shot

import android.content.res.Configuration.*
import android.graphics.Color
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.google.testing.junit.testparameterinjector.TestParameterValuesProvider
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.DevicePreviewInfoParser
import sergio.sastre.composable.preview.scanner.android.device.domain.Navigation.BUTTONS
import sergio.sastre.composable.preview.scanner.android.device.domain.Navigation.GESTURE
import sergio.sastre.composable.preview.scanner.android.device.domain.Orientation
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.FontSizeScale
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.systemui.NavigationConfig
import sergio.sastre.uitesting.utils.testrules.systemui.StatusBarConfig
import sergio.sastre.uitesting.utils.testrules.systemui.SystemUiTestRule
import sergio.sastre.uitesting.utils.testrules.systemui.navigation.Navigation
import sergio.sastre.uitesting.utils.testrules.systemui.statusbar.ClockTime
import sergio.sastre.uitesting.utils.utils.drawToBitmapWithElevation
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForComposeView
import snapshot.testing.recyclerview_previews.shot.utils.setContent
import sergio.sastre.uitesting.utils.common.Orientation as ComposableConfigOrientation

/**
 * First of all, execute the command below to dump the previews under "main" into a json file.
 * For now, ComposablePreviewScanner requires that to be done in a "unit test".
 *    ./gradlew :recyclerviewscreen-previews:shot:testDebugUnitTest --tests 'SaveScanResultInAssets'
 *
 * WARNING: Remember to execute that command again if you modify your Previews or their params.
 *
 *
 * Then record or verify accordingly.
 * 1. Record:
 *    ./gradlew :recyclerviewscreen-previews:shot:executeScreenshotTest -Precord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen-previews:shot:executeScreenshotTest
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

object ComposablePreviewProvider : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner()
            // The name of this file must match the one we dump into in the "unit test"
            .scanFile(
                targetInputStream = getInstrumentation().context.assets.open("scan_result.json"),
                customPreviewsInfoInputStream = getInstrumentation().context.assets.open("custom_previews.json")
            )
            .getPreviews()
}

object SystemUiPreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): SystemUiTestRule {
        val navigation =
            when (DevicePreviewInfoParser.parse(preview.previewInfo.device)?.navigation) {
                BUTTONS -> Navigation.THREE_BUTTON
                GESTURE -> Navigation.GESTURAL
                null -> Navigation.GESTURAL
            }
        return SystemUiTestRule(
            navigationConfig = NavigationConfig(mode = navigation),
            statusBarConfig = StatusBarConfig(clockTime = ClockTime.from("10:00"))
        )
    }
}

object ActivityScenarioForComposablePreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): ActivityScenarioForComposableRule {
        val uiMode =
            when (preview.previewInfo.uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES) {
                true -> UiMode.NIGHT
                false -> UiMode.DAY
            }

        val orientation =
            when (DevicePreviewInfoParser.parse(preview.previewInfo.device)?.orientation == Orientation.LANDSCAPE) {
                true -> ComposableConfigOrientation.LANDSCAPE
                false -> ComposableConfigOrientation.PORTRAIT
            }

        val locale = preview.previewInfo.locale.removePrefix("b+").replace("+","-").ifBlank { "en" }

        val showSystemUi = preview.previewInfo.showSystemUi
        val backgroundColor = when (showSystemUi) {
            true -> null
            false -> Color.TRANSPARENT
        }
        return ActivityScenarioForComposableRule(
            backgroundColor = backgroundColor,
            showStatusBar = showSystemUi,
            config = ComposableConfigItem(
                uiMode = uiMode,
                fontSize = FontSizeScale.Value(preview.previewInfo.fontScale),
                orientation = orientation,
                locale = locale
            )
        )
    }
}

/**
 * TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class ShotComposePreviewTests(
    @TestParameter(valuesProvider = ComposablePreviewProvider::class)
    val preview: ComposablePreview<AndroidPreviewInfo>,
) : ScreenshotTest {

    @get:Rule(order = 0)
    val systemUiRule = SystemUiPreviewRule.createFor(preview)
    @get:Rule(order = 1)
    val composableRule = ActivityScenarioForComposablePreviewRule.createFor(preview)

    @Test
    fun snapPreview() {
        val view = composableRule
            .setContent { preview() }
            .waitForActivity()
            .waitForComposeView()

        val bitmap = when (preview.previewInfo.showSystemUi) {
            true -> systemUiRule.drawFullScreenToBitmap()
            false -> view.drawToBitmapWithElevation()
        }

        compareScreenshot(
            bitmap = bitmap,
            name = AndroidPreviewScreenshotIdBuilder(preview).build()
        )
    }
}