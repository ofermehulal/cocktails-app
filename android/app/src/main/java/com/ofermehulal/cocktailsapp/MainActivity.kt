package com.ofermehulal.cocktailsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.ofermehulal.cocktailsapp.ui.navigation.CocktailsNavigation
import com.ofermehulal.cocktailsapp.ui.theme.CocktailsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            CocktailsAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CocktailsApp()
                }
            }
        }
    }
}

@Composable
fun CocktailsApp() {
    CocktailsNavigation()
}

@Preview(showBackground = true)
@Composable
fun CocktailsAppPreview() {
    CocktailsAppTheme {
        CocktailsApp()
    }
}
