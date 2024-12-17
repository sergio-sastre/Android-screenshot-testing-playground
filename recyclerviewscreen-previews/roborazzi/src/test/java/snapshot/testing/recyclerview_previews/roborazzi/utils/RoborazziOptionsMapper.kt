package snapshot.testing.recyclerview_previews.roborazzi.utils

import com.github.takahirom.roborazzi.Dump
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import sergio.sastre.composable.preview.scanner.core.preview.getAnnotation

object RoborazziOptionsMapper {
    @OptIn(ExperimentalRoborazziApi::class)
    fun createFor(preview: ComposablePreview<AndroidPreviewInfo>): RoborazziOptions =
        preview.getAnnotation<RoborazziConfig>()?.let { config ->
            when(config.enableAccessibility){
                true -> RoborazziOptions(
                    captureType = RoborazziOptions.CaptureType.Dump(
                        explanation = Dump.Companion.AccessibilityExplanation
                    )
                )
                false -> RoborazziOptions()
            }
        } ?: RoborazziOptions()
}