package com.example.road.to.effective.snapshot.testing.lazycolumnscreen

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val lightThemeColors = lightColorScheme(
    primary = Color(0xFF855446),
    secondary = Color(0xFF9C684B),
    tertiary = Color(0xFF03DAC5),
    error = Color(0xFFB00020),
    background = Color.White,

    primaryContainer = Color(0xFFFFDBD1),
    secondaryContainer = Color(0xFFFFDBCA),
    tertiaryContainer = Color(0xFF4FFBE5),
    errorContainer = Color(0xFFFFDAD6),
    surface = Color(0xFFf8FDFF),

    onPrimaryContainer = Color(0xFF3B0900),
    onSecondaryContainer = Color(0xFF331200),
    onTertiaryContainer = Color(0xFF00201C),
    onErrorContainer = Color(0xFF410002),
    onSurface = Color(0xFF001F25),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onError = Color.White,
    onBackground = Color(0xFF001F25),

    outline = Color(0xFF85736E),
    surfaceVariant = Color(0xFFF5DED8),
    onSurfaceVariant = Color(0xFF53433F)
)

val darkThemeColors = darkColorScheme(
    primary = Color(0xFFFFB5A0),
    secondary = Color(0xFFFFB68E),
    tertiary = Color(0xFF17DEC9),
    error = Color(0xFFFFB4AB),
    background = Color(0xFF001F25),

    onPrimary = Color(0xFF5E1702),
    onSecondary = Color(0xFF542200),
    onTertiary = Color(0xFF003731),
    onError = Color(0xFF690005),
    onBackground = Color(0xFFA6EEFF),

    primaryContainer = Color(0xFF7C2D15),
    secondaryContainer = Color(0xFF773300),
    tertiaryContainer = Color(0xFF005048),
    errorContainer = Color(0xFF93000A),
    surface = Color(0xFF001F25),

    onPrimaryContainer = Color(0xFFFFDBD1),
    onSecondaryContainer = Color(0xFFFFDBCA),
    onTertiaryContainer = Color(0xFF4FFBE5),
    onErrorContainer = Color(0xFFFFDAD6),
    onSurface = Color(0xFFA6EEFF),

    outline = Color(0xFF5E1702),
    surfaceVariant = Color(0xFF53433F),
    onSurfaceVariant = Color(0xFFD8C2BC),
)

val LocalAppTheme = staticCompositionLocalOf<(@Composable (content: @Composable () -> Unit) -> Unit)> {
    { content -> DefaultAppTheme(content) } // Default to your real `AppTheme`
}

@SuppressWarnings
@Composable
private fun DefaultAppTheme(
    content: @Composable () -> Unit
) {
    val inDarkMode: Boolean = isSystemInDarkTheme()

    val colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (inDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (inDarkMode) darkThemeColors else lightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@SuppressWarnings
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val appTheme = LocalAppTheme.current
    // Apply the theme (either the default `AppTheme` or an overridden one in tests)
    appTheme(content)
}
