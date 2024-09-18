package snapshot.testing.lazycolumn_previews.roborazzi.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Wallpapers
import com.example.road.to.effective.snapshot.testing.lazycolumnscreen.LocalAppTheme
import com.materialkolor.DynamicMaterialTheme
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

@Composable
fun RenderPreview(
    previewComposable: ComposablePreview<AndroidPreviewInfo>
) {
    val seedColor =
        when (previewComposable.previewInfo.wallpaper) {
            Wallpapers.RED_DOMINATED_EXAMPLE -> Color.Red
            Wallpapers.GREEN_DOMINATED_EXAMPLE -> Color.Green
            Wallpapers.YELLOW_DOMINATED_EXAMPLE -> Color.Yellow
            Wallpapers.BLUE_DOMINATED_EXAMPLE -> Color.Blue
            Wallpapers.NONE -> null
            else -> null
        }

    when (seedColor == null) {
        true -> previewComposable()
        false ->
            CompositionLocalProvider(
                LocalAppTheme provides { content -> DynamicMaterialTheme(seedColor) { content() } }
            ) {
                previewComposable()
            }
    }
}