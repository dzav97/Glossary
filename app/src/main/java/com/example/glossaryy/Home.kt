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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.Quiz.QuizSD
import com.example.glossaryy.ui.theme.GlossaryyTheme

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

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(230.dp))
                    Text(
                        text = "Hai, Elda",
                        fontSize = 18.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.wajah),
                        contentDescription = "Profil",
                        modifier = Modifier.size(30.dp)
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
            fontSize = 18.sp,
            color = Color(0xFFD3D3D3)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Level 1", R.drawable.level1) {
                // Intent untuk berpindah ke QuizSD
                val intent = Intent(context, QuizSD::class.java)
                context.startActivity(intent)
            }
            CategoryCard("Level 2", R.drawable.level2) {
                // Implementasi untuk Level 2 jika diperlukan
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Level 3", R.drawable.level3) {
                // Implementasi untuk Level 3 jika diperlukan
            }
            CategoryCard("Level 4", R.drawable.level4) {
                // Implementasi untuk Level 4 jika diperlukan
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        HomeBottomNavigationBar()
    }
}

@Composable
fun CategoryCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(185.dp)
            .height(160.dp)
            .clickable { onClick() }  // Menambahkan clickable modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()  // Menjamin Column mengisi seluruh ukuran Card
                .padding(20.dp) // Menambahkan padding jika diperlukan
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(85.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))  // Memberi jarak antara gambar dan teks
            Text(
                text = title,
                fontSize = 20.sp,  // Menyesuaikan ukuran font untuk tampil lebih baik
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Composable
fun HomeBottomNavigationBar() {
    BottomAppBar(
        containerColor = Color(0xFF3C0CA6),
        contentColor = Color.White,
        modifier = Modifier.height(90.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.tropy),
                    contentDescription = "Trophy"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.simpan),
                    contentDescription = "Bookmark"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User"
                )
            }
        }
    }
}

