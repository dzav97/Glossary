package com.example.glossaryy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

class PeringkatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PeringkatScreen().Peringkat()
                }
            }
        }
    }
}

class PeringkatScreen {

    @Composable
    fun Peringkat() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF673AB7), Color(0xFF9C27B0))
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "PERINGKAT",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                TopRankItem(name = "Elda", score = 7300, imageRes = R.drawable.wajah, rank = 2)
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TopRankItem(name = "Diaz", score = 7900, imageRes = R.drawable.wajah, rank = 1)
                }
                Spacer(modifier = Modifier.width(16.dp))
                TopRankItem(name = "Fadillah", score = 6800, imageRes = R.drawable.wajah, rank = 3)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                RankItem(rank = 4, name = "Chandra", score = 6700, imageRes = R.drawable.wajah)
                RankItem(rank = 5, name = "Dina", score = 6300, imageRes = R.drawable.wajah)
                RankItem(rank = 6, name = "Faulah", score = 6050, imageRes = R.drawable.wajah)
                RankItem(rank = 7, name = "Dayinta", score = 5900, imageRes = R.drawable.wajah)
                RankItem(rank = 8, name = "Dwi", score = 5400, imageRes = R.drawable.wajah)
                RankItem(rank = 9, name = "Serlya", score = 5210, imageRes = R.drawable.wajah)
                RankItem(rank = 10, name = "Azkha", score = 5030, imageRes = R.drawable.wajah)
            }

            Spacer(modifier = Modifier.height(16.dp))

            BottomNavigationBar()
        }
    }

    @Composable
    fun TopRankItem(name: String, score: Int, imageRes: Int, rank: Int) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFFF9900), shape = CircleShape)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = rank.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Box(
                modifier = Modifier
                    .background(Color(0xFFFF9900), shape = RoundedCornerShape(15.dp))
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(text = score.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }

    @Composable
    fun RankItem(rank: Int, name: String, score: Int, imageRes: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color(0xFFD1C4E9), shape = RoundedCornerShape(10.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = rank.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = score.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun BottomNavigationBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF673AB7))
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
                painter = painterResource(id = R.drawable.tropy),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.simpan),
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
}