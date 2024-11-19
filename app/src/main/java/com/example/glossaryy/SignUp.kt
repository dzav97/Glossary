package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.lifecycle.lifecycleScope
import com.example.glossaryy.database.AppDatabase
import com.example.glossaryy.database.User
import kotlinx.coroutines.launch

class SignUpActivity : ComponentActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.getDatabase(this)  // Inisialisasi Room database
        setContent {
            SignUpScreen()
        }
    }

    @Composable
    fun SignUpScreen() {
        var username = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }
        var name = remember { mutableStateOf("") }
        var dob = remember { mutableStateOf("") }
        var email = remember { mutableStateOf("") }
        val gender = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title of the screen
            Text(
                text = "Daftar",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF3D57A1) // Purple color
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username input
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username") },
                placeholder = { Text("EX : elda") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Icon"
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Password input
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                placeholder = { Text("EX : dwi24") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon"
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Full name input
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nama") },
                placeholder = { Text("EX : Elda Serlya") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Date of birth input
            OutlinedTextField(
                value = dob.value,
                onValueChange = { dob.value = it },
                label = { Text("Tempat, tanggal lahir") },
                placeholder = { Text("EX : Jakarta, 01-01-2000") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Gender radio buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text("Jenis Kelamin")
                Spacer(modifier = Modifier.width(16.dp))
                Row {
                    RadioButton(
                        selected = gender.value == "Laki-Laki",
                        onClick = { gender.value = "Laki-Laki" }
                    )
                    Text("Laki-Laki")
                    Spacer(modifier = Modifier.width(8.dp))
                    RadioButton(
                        selected = gender.value == "Perempuan",
                        onClick = { gender.value = "Perempuan" }
                    )
                    Text("Perempuan")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Email input
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                placeholder = { Text("example@example.com") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions.Default
            )

            Spacer(modifier = Modifier.height(16.dp))

            // SignUp Button
            Button(
                onClick = {
                    // Show a Toast message and navigate to SignInActivity
                    Toast.makeText(applicationContext, "Sign Up Successful!", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3D57A1))
            ) {
                Text("Daftar", color = Color.White)
            }
        }
    }
    }