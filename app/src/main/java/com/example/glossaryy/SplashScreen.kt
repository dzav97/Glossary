package com.example.glossaryy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glossaryy.ui.theme.GlossaryyTheme
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlossaryyTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    fun SplashScreen() {
        LaunchedEffect(Unit) {
            delay(3000)  // 2 seconds delay
            val intent = Intent(this@SplashScreenActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF6200EE)), // Purple background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),  // Logo image resource
                contentDescription = "Glossary Quiz Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Glossary Quiz", color = Color.White, fontSize = 32.sp)
        }
    }
}
