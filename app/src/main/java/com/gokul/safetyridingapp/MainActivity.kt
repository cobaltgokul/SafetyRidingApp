package com.gokul.safetyridingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gokul.safetyridingapp.ui.theme.SafetyRidingAppTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Initialize Google Places SDK ONCE
        if (!Places.isInitialized()) {
            Places.initialize(
                applicationContext,
                getString(R.string.google_maps_key)
            )
        }

        setContent {
            SafetyRidingAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen {
                            navController.navigate("details")
                        }
                    }

                    composable("details") {
                        PersonalDetailsScreen {
                            navController.navigate("home")
                        }
                    }

                    composable("home") {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
