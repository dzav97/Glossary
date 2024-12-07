package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.ui.theme.GlossaryyTheme

class ProfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                // Use Surface composable instead of ShortcutInfoCompat.Surface
                Surface(color = MaterialTheme.colorScheme.background) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF3A007D), // Dark purple
                    Color.White)
            )),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "PROFIL",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        ProfileCard()
        ProfilBottomNavigationBar()
    }
}

@Composable
fun ProfileCard() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Ikon Edit di pojok kanan atas kotak
        IconButton(
            onClick = {
                val intent = Intent(context, ProfilEditActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.TopEnd) // Posisi di pojok kanan atas
                .wrapContentSize() // Menggunakan ukuran default dari gambar asli
        ) {
            Icon(
                painter = painterResource(id = R.drawable.edit), // Ganti dengan ikon edit
                contentDescription = "Edit Profile",
                modifier = Modifier
                    .padding(4.dp) // Opsional: Sesuaikan padding jika diperlukan
            )
        }


        // Konten di dalam kotak
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wajah), // Ganti dengan sumber gambar Anda
                    contentDescription = "Profile picture of Dayinta Ayu Faj'rin",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoBox(text = "Dayinta Ayu Faj'rin")
            ProfileInfoBox(text = "8 April 2006")
            ProfileInfoBox(text = "Perempuan")
            ProfileInfoBox(text = "dayinta88@gmail.com")
            ProfileInfoBox(text = "5900 Poin")
            Divider(color = Color(0xFF6A1B9A), thickness = 2.dp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun ProfileInfoBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center // Mengatur teks di tengah-tengah
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
    }
}



@Composable
fun ProfilBottomNavigationBar() {
    val context = LocalContext.current
    BottomAppBar(
        containerColor = Color(0xFF3C0CA6),
        contentColor = Color.White,
        modifier = Modifier.height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { val intent = Intent(context, Home::class.java)
                context.startActivity(intent) }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { val intent = Intent(context, PeringkatActivity::class.java)
                context.startActivity(intent) }) {
                Icon(
                    painter = painterResource(id = R.drawable.tropy),
                    contentDescription = "Trophy"
                )
            }
            IconButton(onClick = { val intent = Intent(context, QuizHistoryActivity::class.java)
                context.startActivity(intent) }) {
                Icon(
                    painter = painterResource(id = R.drawable.simpan),
                    contentDescription = "Bookmark"
                )
            }
            IconButton(onClick = { val intent = Intent(context, ProfilActivity::class.java)
                context.startActivity(intent) }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User"
                )
            }
        }
    }
}