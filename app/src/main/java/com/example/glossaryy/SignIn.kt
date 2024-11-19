package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

        /*Column(
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
        }*/

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Lapisan pertama: warna putih
        ) {
            // Lapisan kedua: gradasi
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(y = 310.dp)
                    //.height(450.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp, // Radius melengkung di sudut kiri atas
                            topEnd = 30.dp,   // Radius melengkung di sudut kanan atas
                            bottomStart = 0.dp, // Tidak ada lengkungan di bawah
                            bottomEnd = 0.dp    // Tidak ada lengkungan di bawah
                        )
                    )// Bentuk melengkung
                    .background(
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.04f to Color(0xA8381E72), // 22% warna ungu
                                1.0f to Color(0xFFF497BF)  // 100% warna pink
                            ),
                            start = Offset(0f, 0f), // Titik awal (kiri)
                            end = Offset(1000f, 0f) // Titik akhir (kanan)
                        )
                    )
                    .align(Alignment.BottomCenter) // Dari tengah ke bawah
            )

            // Lapisan ketiga: Card putih dengan ujung melengkung di atas lapisan kedua
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(y = 335.dp)
                    //.height(420.dp)
                    .align(Alignment.BottomCenter)
                    .clip(
                        RoundedCornerShape(
                            topStart = 25.dp, // Lengkungan di sudut kiri atas
                            topEnd = 25.dp,   // Lengkungan di sudut kanan atas
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                            )
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = MaterialTheme.shapes.large, // Pinggir melengkung
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Judul "Masuk"
                    Text(
                        text = "Masuk",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF3D57A1)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Input username
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
                    // Spacer antara input username dan "Lupa nama pengguna"
                    Spacer(modifier = Modifier.height(2.dp))

                    // Lupa nama pengguna
                    TextButton(
                        onClick = { /* Handle forgot username */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Lupa nama pengguna", color = Color(0xFF3D57A1))
                    }
                    // Spacer antara "Lupa nama pengguna" dan input password
                    Spacer(modifier = Modifier.height(8.dp))

                    // Input password
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
                    // Spacer antara input password dan "Lupa sandi"
                    Spacer(modifier = Modifier.height(3.dp))

                    // Lupa sandi
                    TextButton(
                        onClick = { /* Handle forgot password */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Lupa sandi", color = Color(0xFF3D57A1))
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tombol login
                    Button(
                        onClick = {
                            if (username.value == "elda" && password.value == "dwi24") {
                                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Invalid username or password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3C0CA6)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Masuk", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tombol daftar
                    TextButton(
                        onClick = {
                            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Belum punya akun? Daftar Sekarang",
                            color = Color(0xFF3D57A1)
                        )
                    }
                }
            }

            // Lapisan pertama: Teks "Glossary" dan logo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp), // Posisi di atas
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GLOSSARY",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF3D57A1)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tambahkan logo dari drawable atau asset lokal
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ganti dengan logo sesuai kebutuhan
                    contentDescription = "Logo",
                    //tint = Color(0xFF3D57A1),
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }
}
