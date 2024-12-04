package snapshot.testing.lazycolumn_previews.roborazzi.utils

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import org.robolectric.RuntimeEnvironment.setFontScale
import org.robolectric.RuntimeEnvironment.setQualifiers
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowDisplay
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.domain.RobolectricDeviceQualifierBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import kotlin.math.roundToInt

object RobolectricPreviewInfosApplier {
    fun applyFor(
        preview: ComposablePreview<AndroidPreviewInfo>,
    ) {
        setDevice(preview.previewInfo.device)
        setUiMode(preview.previewInfo.uiMode)
        setLocale(preview.previewInfo.locale)
        setFontScale(preview.previewInfo.fontScale)
    }
}

private fun setDevice(device: String) {
    val parsedDevice =
        RobolectricDeviceQualifierBuilder.build(device) ?: RobolectricDeviceQualifiers.Pixel4a
    setQualifiers(parsedDevice)
}

private fun setUiMode(uiMode: Int) {
    val nightMode =
        when (uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            true -> "night"
            false -> "notnight"
        }
    setQualifiers("+$nightMode")
}

private fun setLocale(locale: String) {
    val localeWithFallback = locale.ifBlank { "en" }
    setQualifiers("+$localeWithFallback")
}

fun Activity.setBackgroundColor(
    showBackground: Boolean,
    backgroundColor: Long,
) {
    when (showBackground) {
        false -> window.decorView.setBackgroundColor(Color.TRANSPARENT)
        true -> if (backgroundColor != 0L) {
            window.decorView.setBackgroundColor(backgroundColor.toInt())
        }
    }
}

fun Activity.setShadowDisplay(
    widthDp: Int,
    heightDp: Int
) {
    if (widthDp > 0 || heightDp > 0) {
        val display = ShadowDisplay.getDefaultDisplay()
        val density = resources.displayMetrics.density
        if (widthDp > 0) {
            widthDp.let {
                val widthPx = (widthDp * density).roundToInt()
                Shadows.shadowOf(display).setWidth(widthPx)
            }
        }
        if (heightDp > 0) {
            heightDp.let {
                val heightPx = (heightDp * density).roundToInt()
                Shadows.shadowOf(display).setHeight(heightPx)
            }
        }
        recreate()
    }
}

fun ComposablePreview<AndroidPreviewInfo>.resize(
    widthDp: Int,
    heightDp: Int,
): @Composable () -> Unit {
    val resizedPreview = @Composable {
        val modifier = when {
            widthDp > 0 && heightDp > 0 -> Modifier.size(widthDp.dp, heightDp.dp)
            widthDp > 0 -> Modifier.width(widthDp.dp)
            heightDp > 0 -> Modifier.height(heightDp.dp)
            else -> Modifier
        }
        Box(modifier = modifier) { this@resize.invoke() }
    }
    return resizedPreview
}
