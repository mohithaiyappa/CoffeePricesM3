package tk.mohithaiyappa.coffeepricesm3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import tk.mohithaiyappa.coffeepricesm3.ui.home.HomeScreen
import tk.mohithaiyappa.coffeepricesm3.ui.home.HomeViewModel
import tk.mohithaiyappa.coffeepricesm3.ui.theme.CoffeePricesM3Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeePricesM3Theme {
                val viewModel = hiltViewModel<HomeViewModel>()
                val homeUiState by viewModel.uiState.collectAsState()
                HomeScreen(uiState = homeUiState)
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    CoffeePricesM3Theme {
        Greeting("Android")
    }
}
