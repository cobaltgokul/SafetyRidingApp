package com.gokul.safetyridingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonalDetailsScreen(onSave: () -> Unit) {

    var step by remember { mutableStateOf(1) }

    // Common
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    // Emergency contacts
    var c1Name by remember { mutableStateOf("") }
    var c1Phone by remember { mutableStateOf("") }
    var c2Name by remember { mutableStateOf("") }
    var c2Phone by remember { mutableStateOf("") }
    var c3Name by remember { mutableStateOf("") }
    var c3Phone by remember { mutableStateOf("") }

    // Medical
    var bloodGroup by remember { mutableStateOf("") }
    var medicalNote by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = if (step == 1) "Emergency Setup" else "Medical Information",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (step == 1) {
            // -------- PAGE 1 --------

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name *") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number *") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            Text("Emergency Contacts", fontSize = 16.sp)

            EmergencyContact("Contact 1 (Required)", c1Name, c1Phone,
                { c1Name = it }, { c1Phone = it })

            EmergencyContact("Contact 2 (Required)", c2Name, c2Phone,
                { c2Name = it }, { c2Phone = it })

            EmergencyContact("Contact 3 (Optional)", c3Name, c3Phone,
                { c3Name = it }, { c3Phone = it })

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { step = 2 },
                modifier = Modifier.fillMaxWidth(),
                enabled = fullName.isNotBlank()
                        && phone.isNotBlank()
                        && c1Name.isNotBlank()
                        && c1Phone.isNotBlank()
                        && c2Name.isNotBlank()
                        && c2Phone.isNotBlank()
            ) {
                Text("Next")
            }

        } else {
            // -------- PAGE 2 --------

            OutlinedTextField(
                value = bloodGroup,
                onValueChange = { bloodGroup = it },
                label = { Text("Blood Group (Optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = medicalNote,
                onValueChange = { medicalNote = it },
                label = { Text("Medical Notes (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
private fun EmergencyContact(
    title: String,
    name: String,
    phone: String,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit
) {
    Spacer(Modifier.height(12.dp))
    Text(title)

    Spacer(Modifier.height(6.dp))

    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(6.dp))

    OutlinedTextField(
        value = phone,
        onValueChange = onPhoneChange,
        label = { Text("Phone Number") },
        modifier = Modifier.fillMaxWidth()
    )
}
