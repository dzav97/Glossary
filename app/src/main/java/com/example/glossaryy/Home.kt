package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.Quiz.QuizSD
import com.example.glossaryy.ui.theme.GlossaryyTheme
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.glossaryy.Quiz.QuizSMA
import com.example.glossaryy.Quiz.QuizSMP
import com.example.glossaryy.Quiz.QuizUmum


class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var showAppInfo by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { HomeBottomNavigationBar() } // Navbar tetap di bagian bawah
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Mencegah konten tertutup navbar
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF381E72), Color(0xFFFFFFEE))
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "GLOSSARY",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(80.dp))

            Card(
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, ProfilActivity::class.java)
                        context.startActivity(intent)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Hi, Elda",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.wajah),
                            contentDescription = "Profil",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    val intent = Intent(context, ProfilActivity::class.java)
                                    context.startActivity(intent)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Point",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(2.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "5900",
                            fontSize = 36.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Kategori Kuis",
                fontSize = 18.sp,
                color = Color(0xFFffffff)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Gunakan LazyRow di sini untuk kategori kuis
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    CategoryCard("Level 1", R.drawable.level1) {
                        val intent = Intent(context, QuizSD::class.java)
                        context.startActivity(intent)
                    }
                }
                item {
                    CategoryCard("Level 2", R.drawable.level2) {
                        val intent = Intent(context, QuizSMP::class.java)
                        context.startActivity(intent)
                    }
                }
                item {
                    CategoryCard("Level 3", R.drawable.level3) {
                        val intent = Intent(context, QuizSMA::class.java)
                        context.startActivity(intent)
                    }
                }
                item {
                    CategoryCard("Level 4", R.drawable.level4) {
                        val intent = Intent(context, QuizUmum::class.java)
                        context.startActivity(intent)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            /*Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryCard("Level 1", R.drawable.level1) {
                    val intent = Intent(context, QuizSD::class.java)
                    context.startActivity(intent)
                }
                CategoryCard("Level 2", R.drawable.level2) {
                    // Navigasi ke halaman kuis level 2
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryCard("Level 3", R.drawable.level3) {
                    // Navigasi ke halaman kuis level 3
                }
                CategoryCard("Level 4", R.drawable.level4) {
                    // Navigasi ke halaman kuis level 4
                }
            }*/

            // Panah untuk menampilkan info aplikasi

            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Info Aplikasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF381E72)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Memberi jarak antara ikon dan teks

                IconButton(
                    onClick = { showAppInfo = !showAppInfo },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = if (showAppInfo) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = if (showAppInfo) "Hide Info" else "Show Info",
                        tint = Color(0xFF381E72),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            // Info aplikasi dengan animasi
            AnimatedVisibility(
                visible = showAppInfo,
                enter = androidx.compose.animation.fadeIn(animationSpec = tween(500)),
                exit = androidx.compose.animation.fadeOut(animationSpec = tween(500))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFFEFEFEF), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    /*Text(
                        text = "Info Aplikasi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF381E72)
                    )
                    Spacer(modifier = Modifier.height(8.dp))*/
                    Text(
                        text = "Aplikasi Glossaryy adalah platform edukasi yang menyediakan berbagai kuis menarik untuk meningkatkan pengetahuan Anda. Nikmati pengalaman belajar yang interaktif dengan beragam kategori kuis dan fitur peringkat yang membuat belajar menjadi menyenangkan!",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ClickableText(
                        text = androidx.compose.ui.text.AnnotatedString("Pelajari lebih lanjut di website kami."),
                        onClick = { /* Tambahkan navigasi ke URL atau informasi lebih lanjut */ },
                        style = androidx.compose.ui.text.TextStyle(color = Color(0xFF3C0CA6), fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .height(160.dp)
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(85.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeBottomNavigationBar() {
    val context = LocalContext.current

    BottomAppBar(
        containerColor = Color(0xFF3C0CA6),
        contentColor = Color.White,
        modifier = Modifier
            .height(80.dp)
            //.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = {
                val intent = Intent(context, PeringkatActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.tropy),
                    contentDescription = "Trophy"
                )
            }
            IconButton(onClick = {
                val intent = Intent(context, QuizHistoryActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.simpan),
                    contentDescription = "Bookmark"
                )
            }
            IconButton(onClick = {
                val intent = Intent(context, ProfilActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User"
                )
            }
        }
    }
}
