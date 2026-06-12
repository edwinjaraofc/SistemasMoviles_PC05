package com.example.gymtrack.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val GymColorScheme = lightColorScheme(
    primary = PrimaryGym,
    onPrimary = OnPrimaryGym,
    secondary = SecondaryGym,
    tertiary = TertiaryGym,
    background = BackgroundGym,
    onBackground = OnBackgroundGym,
    surface = SurfaceGym,
    onSurface = OnSurfaceGym
)

@Composable
fun GymTrackTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = GymColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = GymColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
