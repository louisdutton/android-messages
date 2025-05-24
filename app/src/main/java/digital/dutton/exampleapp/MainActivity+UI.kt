package digital.dutton.example

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import digital.dutton.example.coordinators.languageCoordinator.LanguageCoordinator
import digital.dutton.example.models.languageContent.UIContent
import digital.dutton.example.models.states.ExperienceStates
import digital.dutton.example.ui.screens.HUD
import digital.dutton.example.ui.screens.LandingScreen
import digital.dutton.example.ui.screens.MenuScreen
import digital.dutton.example.ui.styleguide.HeaderText
import digital.dutton.example.ui.styleguide.theme.AppTheme

fun MainActivity.setupUI() {
    val currentContent: UIContent = LanguageCoordinator.shared.currentContent ?: return
    setContent {
        AppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                when (state.value) {
                    ExperienceStates.LANDING -> LandingScreen()
                    ExperienceStates.MENU -> MenuScreen()
                }
                HUD(state = state.value)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    HeaderText(
        copy = "Hello $name!",
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}
