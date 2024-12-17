package snapshot.testing.recyclerview_previews.android_testify

import android.content.res.Configuration.*
import android.graphics.Color
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.google.testing.junit.testparameterinjector.TestParameterValuesProvider
import dev.testify.annotation.ScreenshotInstrumentation
import dev.testify.core.TestifyConfiguration
import dev.testify.core.processor.capture.pixelCopyCapture
import dev.testify.scenario.ScreenshotScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.DevicePreviewInfoParser
import sergio.sastre.composable.preview.scanner.android.device.domain.Orientation
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation
import sergio.sastre.uitesting.android_testify.screenshotscenario.assertSame
import sergio.sastre.uitesting.android_testify.screenshotscenario.generateDiffs
import sergio.sastre.uitesting.android_testify.screenshotscenario.setContent
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForComposableRule
import sergio.sastre.uitesting.utils.activityscenario.ComposableConfigItem
import sergio.sastre.uitesting.utils.common.FontSizeScale
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.testrules.animations.DisableAnimationsRule
import snapshot.testing.recyclerview_previews.android_testify.utils.AndroidTestifyConfig
import sergio.sastre.uitesting.utils.common.Orientation as ComposableConfigOrientation

/**
 * First of all, execute the command below to dump the previews under "main" into a json file.
 * For now, ComposablePreviewScanner requires that to be done in a "unit test", but will not be necessary from 0.5.0 on.
 *    ./gradlew :recyclerviewscreen-previews:android-testify:testDebugUnitTest --tests 'SaveScanResultInAssets'
 *
 * WARNING: Remember to execute that command again if you modify your Previews or their params.
 *
 *
 * Then record or verify accordingly.
 * 1. Record:
 *    ./gradlew :recyclerviewscreen-previews:android-testify:screenshotRecord
 * 2. Verify:
 *    ./gradlew :recyclerviewscreen-previews:android-testify:screenshotTest
 *
 * With Gradle Managed Devices (API 27+)
 * 1. Record (saved under this module's build/outputs/managed_device_android_test_additional_output/...):
 *    ./gradlew :recyclerviewscreen-previews:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -PrecordModeGmd -Pandroid.testoptions.manageddevices.emulator.gpu=host
 * 2. Verify (copy recorded screenshots + assert):
 *  - Copy recorded screenshots in androidTest/assets -> https://ndtp.github.io/android-testify/docs/recipes/gmd
 *    ./gradlew :recyclerviewscreen-previews:android-testify:copyScreenshots -Pdevices=pixel3api30
 *  - Assert
 *    ./gradlew :recyclerviewscreen-previews:android-testify:pixel3api30DebugAndroidTest -PuseTestStorage -Pandroid.testoptions.manageddevices.emulator.gpu=host
 *
 * INFO: It uses emulator.gpu=host to avoid it fails when verifying screenshots with elevation on the same machine
 *
 * To run them using Android Orchestrator, add the following at the end of the command:
 * -PuseOrchestrator
 */

object ComposablePreviewProvider : TestParameterValuesProvider() {
    override fun provideValues(context: Context?): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner()
            // The name of this file must match the one we dump into in the "unit test"
            .scanFile(getInstrumentation().context.assets.open("scan_result.json"))
            .getPreviews()
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

        val locale =
            preview.previewInfo.locale.removePrefix("b+").replace("+", "-").ifBlank { "en" }

        return ActivityScenarioForComposableRule(
            backgroundColor = Color.TRANSPARENT,
            config = ComposableConfigItem(
                uiMode = uiMode,
                fontSize = FontSizeScale.Value(preview.previewInfo.fontScale),
                orientation = orientation,
                locale = locale
            )
        )
    }
}

object ScreenshotScenarioPreviewRule {
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): ScreenshotScenarioRule =
        preview.getAnnotation<AndroidTestifyConfig>()?.let { config ->
            ScreenshotScenarioRule(configuration = TestifyConfiguration(exactness = config.exactness))
        } ?: ScreenshotScenarioRule()
}

/**
 * TestParameterInjector requires API 24+ to run with instrumented tests.
 * It throws java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder in lower APIs.
 * Parameterized Runner is compatible with instrumented test of any API level
 */
@SdkSuppress(minSdkVersion = 24)
@RunWith(TestParameterInjector::class)
class AndroidTestifyComposePreviewTests(
    @TestParameter(valuesProvider = ComposablePreviewProvider::class)
    val preview: ComposablePreview<AndroidPreviewInfo>,
) {

    @get:Rule(order = 0)
    var disableAnimationsRule = DisableAnimationsRule()

    @get:Rule(order = 1)
    val composableRule = ActivityScenarioForComposablePreviewRule.createFor(preview)

    @get:Rule(order = 2)
    var screenshotRule = ScreenshotScenarioPreviewRule.createFor(preview)

    @ScreenshotInstrumentation
    @Test
    fun snapPreview() {
        screenshotRule
            .withScenario(composableRule.activityScenario)
            .setScreenshotViewProvider {
                composableRule.setContent { preview() }.composeView
            }
            .configure { captureMethod = ::pixelCopyCapture }
            .generateDiffs(true)
            .assertSame(
                name = AndroidPreviewScreenshotIdBuilder(preview).build()
            )
    }
}