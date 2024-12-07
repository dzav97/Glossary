package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.ui.theme.GlossaryyTheme

class QuizHistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                QuizHistoryScreen()
            }
        }
    }
}

@Composable
fun QuizHistoryScreen() {
    // Gunakan Scaffold untuk tata letak
    Scaffold(
        bottomBar = { HistoryBottomNavigationBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Hindari navbar menutupi konten
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF381E72), Color(0xFFFFFFFF))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Aktifkan scrolling
                    .padding(16.dp)
            ) {
                // Header
                Text(
                    text = "RIWAYAT KUIS",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(50.dp))

                // Daftar item kuis
                QuizItem(
                    title = "SD",
                    score = 3900,
                    points = 760,
                    accuracy = 80,
                    imageRes = R.drawable.sd
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "SMP",
                    score = 2440,
                    points = 679,
                    accuracy = 67,
                    imageRes = R.drawable.smp
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "SMA",
                    score = 5900,
                    points = 2000,
                    accuracy = 86,
                    imageRes = R.drawable.sma
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "UMUM",
                    score = 4789,
                    points = 1389,
                    accuracy = 83,
                    imageRes = R.drawable.umum
                )
            }
        }
    }
}

@Composable
fun QuizItem(title: String, score: Int, points: Int, accuracy: Int, imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(20.dp))
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(Color(0xFFB6A4FF), shape = RoundedCornerShape(10.dp))
                    .padding(6.dp)
            ) {
                Column {
                    Text(
                        text = "Score : $score",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Point : $points",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Akurasi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Color(0xFFBDBDBD), shape = RoundedCornerShape(10.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((accuracy * 3).dp)
                    .background(Color(0xFF673AB7), shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "$accuracy%",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun HistoryBottomNavigationBar() {
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

/*package com.example.glossaryy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.ui.theme.GlossaryyTheme

class QuizHistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuizHistoryScreen().QuizHistory()
                }
            }
        }
    }
}


class QuizHistoryScreen {

    @Composable
    fun QuizHistory() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color( 0xFF381E72), Color(0xFFFFFFFF))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp) // Adjust padding to avoid overlapping with the navbar
                    .padding(16.dp)
            ) {
                Text(
                    text = "RIWAYAT KUIS",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(50.dp)) // Increase space between title and first item

                QuizItem(
                    title = "SD",
                    score = 3900,
                    points = 760,
                    accuracy = 80,
                    imageRes = R.drawable.sd
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "SMP",
                    score = 2440,
                    points = 679,
                    accuracy = 67,
                    imageRes = R.drawable.smp
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "SMA",
                    score = 5900,
                    points = 2000,
                    accuracy = 86,
                    imageRes = R.drawable.sma
                )
                Spacer(modifier = Modifier.height(16.dp))
                QuizItem(
                    title = "UMUM",
                    score = 4789,
                    points = 1389,
                    accuracy = 83,
                    imageRes = R.drawable.umum
                )
            }

            BottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    fun QuizItem(title: String, score: Int, points: Int, accuracy: Int, imageRes: Int) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Blue, shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .padding(12.dp) // Adjust padding to make the box smaller
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp) // Adjust size to make the image smaller
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(67.dp)) // Adjust spacing to make the layout more compact
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically) // Center the text vertically
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally) // Center the text horizontally
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Push the score box to the right
                Box(
                    modifier = Modifier
                        .background(Color(0xFFB6A4FF), shape = RoundedCornerShape(10.dp))
                        .padding(6.dp) // Adjust padding to make the box smaller
                ) {
                    Column {
                        Text(
                            text = "Score : $score",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Point : $points",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Akurasi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color(0xFFBDBDBD), shape = RoundedCornerShape(10.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((accuracy * 3).dp)
                        .background(Color(0xFF673AB7), shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = "$accuracy%",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF3C0CA6))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.tropy), // Ganti 'tropy' dengan 'trophy'
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.simpan), // Ganti 'simpan' dengan 'bookmark'
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}*/