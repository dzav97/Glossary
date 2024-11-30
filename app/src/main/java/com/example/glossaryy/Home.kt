package com.example.glossaryy

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.glossaryy.Quiz.QuizSD
import com.example.glossaryy.ui.theme.GlossaryyTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                val navController = rememberNavController()  // Membuat NavController
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Menambahkan NavHost untuk menentukan rute halaman
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            MainScreen(navController)  // Panggil MainScreen dengan navController
                        }
                        composable("quiz") {
                            QuizSD()  // Rute untuk halaman QuizSD
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF381E72), Color(0xFFFFFFEE))
                )
            )
    ) {
        Text(
            text = "GLOSSARY",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White,
            modifier = Modifier.padding(50.dp)
        )
        Spacer(modifier = Modifier.height(1.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.wajah),
                        contentDescription = "User Avatar",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hai, Elda",
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Kategori Kuis",
            fontSize = 20.sp,
            color = Color(0xFFD3D3D3),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Mengubah fungsi CategoryCard untuk memicu navigasi ke Quiz
            CategoryCard("Level 1", R.drawable.level1, navController)
            CategoryCard("Level 2", R.drawable.level2, navController)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Level 3", R.drawable.level3, navController)
            CategoryCard("Level 4", R.drawable.level4, navController)
        }
        Spacer(modifier = Modifier.weight(1f))
        HomeBottomNavigationBar()
    }
}

@Composable
fun CategoryCard(title: String, imageRes: Int, navController: NavController) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(9.dp),
        modifier = Modifier
            .width(180.dp)
            .height(150.dp)
            .clickable {
                // Pindah ke halaman quiz saat Level 1 diklik
                if (title == "Level 1") {
                    navController.navigate("quiz")
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = title,
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun HomeBottomNavigationBar() {
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
            IconButton(onClick = { /* Navigasi ke Home */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { /* Navigasi ke Trophy */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tropy),
                    contentDescription = "Trophy"
                )
            }
            IconButton(onClick = { /* Navigasi ke Bookmark */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.simpan),
                    contentDescription = "Bookmark"
                )
            }
            IconButton(onClick = { /* Navigasi ke User */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User"
                )
            }
        }
    }
}
