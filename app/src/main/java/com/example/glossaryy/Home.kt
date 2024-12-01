package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.glossaryy.Quiz.QuizSD
import com.example.glossaryy.Quiz.QuizSMP
import com.example.glossaryy.ui.theme.GlossaryyTheme
import androidx.compose.ui.platform.LocalContext
import com.example.glossaryy.Quiz.QuizSMA
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
    val currentContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF381E72), Color(0xFFFFFFEE))
                )
            )
            //.padding(16.dp)
    ) {
        Text(
            text = "GLOSSARY",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.wajah),
                        contentDescription = "user stars",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hai, Dayinta",
                        fontSize = 18.sp
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

        //kategori kuis
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Kategori Kuis",
            fontSize = 18.sp,
            color = Color(0xFFD3D3D3),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        //SD SMP
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("SD", R.drawable.sd) {
                val intent = Intent(currentContext, QuizSD::class.java)
                currentContext.startActivity(intent)

            }
            CategoryCard("SMP", R.drawable.smp) {
                val intent = Intent(currentContext, QuizSMP::class.java)
                currentContext.startActivity(intent)

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        //SMA & Umum
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("SMA", R.drawable.sma) {
                val intent = Intent(currentContext, QuizSMA::class.java)
                currentContext.startActivity(intent)

            }
            CategoryCard("UMUM", R.drawable.umum) {
                val intent = Intent(currentContext, QuizUmum::class.java)
                currentContext.startActivity(intent)

            }
        }

        // Bottom Navigation
        Spacer(modifier = Modifier.weight(1f))

        //HomeBottomNavigationBar()

        HomeBottomNavigationBar { destination ->
            when (destination) {
                "home" -> {
                    // Tetap di halaman Home
                }
                "leaderboard" -> {
                    val intent = Intent(currentContext, PeringkatActivity::class.java)
                    currentContext.startActivity(intent)
                }
                "history" -> {
                    val intent = Intent(currentContext, QuizHistoryActivity::class.java)
                    currentContext.startActivity(intent)
                }
                "profile" -> {
                    val intent = Intent(currentContext, ProfilActivity::class.java)
                    currentContext.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(160.dp)
            .height(120.dp)
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun HomeBottomNavigationBar(onNavigate: (String) -> Unit) {
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
            IconButton(onClick = { onNavigate("home")}) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = {  onNavigate("leaderboard") }) {
                Icon(
                    painter = painterResource(id = R.drawable.tropy),
                    contentDescription = "leaderboard"
                )
            }
            IconButton(onClick = { onNavigate("history") }) {
                Icon(
                    painter = painterResource(id = R.drawable.simpan),
                    contentDescription = "history"
                )
            }
            IconButton(onClick = { onNavigate("profil") }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "profil"
                )
            }
        }
    }
}

