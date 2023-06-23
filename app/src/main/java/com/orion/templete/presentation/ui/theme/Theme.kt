package com.orion.templete.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimaryDark,
    onPrimary = ColorOnPrimaryDark,
    primaryContainer = ColorPrimaryVariantDark,
    onPrimaryContainer = ColorOnPrimaryDark,
    inversePrimary = ColorOnPrimaryDark,
    secondary = ColorSecondaryDark,
    onSecondary = ColorOnSecondaryDark,
    secondaryContainer = ColorSecondaryVariantDark,
    onSecondaryContainer = ColorOnSecondaryDark,
    tertiary = ColorTextTertiaryDark,
    onTertiary = ColorTextTertiaryLight,
    tertiaryContainer = ColorTextTertiaryDark,
    onTertiaryContainer = ColorTextTertiaryLight,
    background = ColorBackgroundDark,
    onBackground = ColorOnBackgroundDark,
    surface = ColorSurfaceDark,
    onSurface = ColorOnSurfaceDark,
    surfaceVariant = ColorSurfaceLight,
    onSurfaceVariant = ColorOnSurfaceLight,
    surfaceTint = ColorPrimaryDark,
    inverseSurface = ColorOnSurfaceLight,
    inverseOnSurface = ColorOnSurfaceLight,
    error = ColorErrorDark,
    onError = ColorOnErrorDark,
    errorContainer = ColorErrorDark,
    onErrorContainer = ColorOnErrorDark,
    outline = ColorInputBorderDark,
    outlineVariant = ColorInputBorderLight,
    scrim = ColorSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimaryLight,
    onPrimary = ColorOnPrimaryLight,
    primaryContainer = ColorPrimaryVariantLight,
    onPrimaryContainer = ColorOnPrimaryDark,
    inversePrimary = ColorOnPrimaryDark,
    secondary = ColorSecondaryLight,
    onSecondary = ColorOnSecondaryLight,
    secondaryContainer = ColorSecondaryVariantLight,
    onSecondaryContainer = ColorOnSecondaryDark,
    tertiary = ColorTextTertiaryLight,
    onTertiary = ColorTextTertiaryDark,
    tertiaryContainer = ColorTextTertiaryLight,
    onTertiaryContainer = ColorTextTertiaryDark,
    background = ColorBackgroundLight,
    onBackground = ColorOnBackgroundLight,
    surface = ColorSurfaceLight,
    onSurface = ColorOnSurfaceLight,
    surfaceVariant = ColorSurfaceDark,
    onSurfaceVariant = ColorOnSurfaceDark,
    surfaceTint = ColorPrimaryLight,
    inverseSurface = ColorOnSurfaceDark,
    inverseOnSurface = ColorOnSurfaceDark,
    error = ColorErrorLight,
    onError = ColorOnErrorLight,
    errorContainer = ColorErrorLight,
    onErrorContainer = ColorOnErrorLight,
    outline = ColorInputBorderLight,
    outlineVariant = ColorInputBorderDark,
    scrim = ColorSurfaceLight
)

@Composable
fun TempleteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}