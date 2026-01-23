package com.gokul.safetyridingapp

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val activity = context as Activity

    var startLocation by remember { mutableStateOf("Detecting...") }
    var destination by remember { mutableStateOf("") }

    var startLatLng by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var destLatLng by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    // Init Places once
    LaunchedEffect(Unit) {
        if (!Places.isInitialized()) {
            Places.initialize(context, "YOUR_API_KEY_HERE")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text("Plan Your Ride", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        // START LOCATION (AUTO / MANUAL LATER)
        OutlinedTextField(
            value = startLocation,
            onValueChange = {},
            label = { Text("Start Location") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // DESTINATION PICKER
        Button(
            onClick = {
                val fields = listOf(
                    Place.Field.NAME,
                    Place.Field.LAT_LNG
                )

                val intent = Autocomplete.IntentBuilder(
                    AutocompleteActivityMode.FULLSCREEN,
                    fields
                ).build(context)

                activity.startActivityForResult(intent, 1001)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Destination")
        }

        Spacer(Modifier.height(12.dp))

        if (destination.isNotEmpty()) {
            Text("Destination: $destination")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                // SEND DATA TO FIREBASE
                // startLatLng + destLatLng
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = destLatLng != null
        ) {
            Text("Start Trip")
        }
    }
}
