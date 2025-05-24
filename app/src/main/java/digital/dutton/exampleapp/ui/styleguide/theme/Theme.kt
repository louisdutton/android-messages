package digital.dutton.example.ui.styleguide.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
    darkColorScheme(
        background = Black,
        primary = White,
    )

private val LightColorScheme =
    lightColorScheme(
        background = White,
        primary = Black,
    )

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
  val colorScheme =
      when {
        isDynamicColor && isDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        isDynamicColor && !isDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
      }

  MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content,
  )
}
