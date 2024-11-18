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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.glossaryy.database.AppDatabase
import kotlinx.coroutines.launch

class SignInActivity : ComponentActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.getDatabase(this)
        setContent {
            SignInScreen()
        }
    }

    @Composable
    fun SignInScreen() {
        var username = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Text
            Text(
                text = "GLOSSARY",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF3D57A1) // Purple color
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Username Input Field
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Pengguna") },
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

            // Forgot Username Text
            TextButton(
                onClick = { /* Handle forgot username */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Lupa nama pengguna", color = Color(0xFF3D57A1))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input Field
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Sandi") },
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

            // Forgot Password Text
            TextButton(
                onClick = { /* Handle forgot password */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Lupa sandi", color = Color(0xFF3D57A1))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Login Button
            Button(
                onClick = {
                    // Simulate successful login and navigate to the next screen
                    if (username.value == "elda" && password.value == "dwi24") {
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors( Color(0xFF3D57A1) // Set background color directly
                )
            ) {
                Text("Masuk", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Text Button
            TextButton(
                onClick = {
                    val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                    startActivity(intent)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Belum punya akun? Daftar Sekarang", color = Color(0xFF3D57A1))
            }
        }
    }
}
