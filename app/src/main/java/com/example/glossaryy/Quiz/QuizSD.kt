package com.example.glossaryy.Quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizSD : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp()
        }
    }
}

@Composable
fun QuizApp() {
    val questions = listOf(
        QuestionSD("Sebuah tabung memiliki jari-jari 7 cm dan tinggi 10 cm. Berapakah volume tabung tersebut?", listOf("1540 cm3", "1352 cm3", "1232 cm3", "1496 cm3"), 0),
        QuestionSD("Jika luas persegi adalah 144cm2, berapakah panjang sisinya?", listOf("10cm", "12cm", "14cm", "16cm"), 1),
        QuestionSD("Planet manakah yang paling dekat dengan matahari?", listOf("Venus", "Mars", "Merkurius", "Bumi"), 2),
        QuestionSD("Manakah dibawah ini yang merupakan sumber energi terbarukan?", listOf("Batubara", "Minyak Bumi", "Angin", "Gas Alam"), 2),
        QuestionSD("Siapakah proklamator Kemerdekaan Indonesia?", listOf("Mohammad Hatta dan Soekarno", "Soeharto dan Soekarno", "Mohammad Hatta dan Soeharto", "Soekarno dan Jusuf Kalla"), 0),
        QuestionSD("Apa nama ibu kota Indonesia?", listOf("Jakarta", "Bandung", "Surabaya", "Semarang"), 0),
        QuestionSD("Apa yang dimaksud dengan 'Sinonim'?", listOf("Kata yang berlawanan makna", "Kata yang sama makna", "Kata yang hampir punah", "Kata yang yang sering digunakna"), 1),
        QuestionSD("Cermatilah kalimat berikut: 'Ani sedang membaca buku di taman.'Kata 'di taman' dalam kalimat tersebut merupakan jenis keterangan apa?", listOf("Keterangan Waktu", "Keterangan cara", "Keterangan alasan", "Keterangan tempat"), 3),
        QuestionSD("Choose the corrent form of the verb: 'She ---- to the market yesterday", listOf("go","went","goes","going"), 1),
        QuestionSD("What is the plural form of'child'", listOf("Childs", "Chlidren", "Childes", "Childeren"),1)
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var answerShown by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    val scope = rememberCoroutineScope()

    fun goToNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            selectedAnswerIndex = null
            answerShown = false
            timeLeft = 10 // Reset timer
        }
    }

    LaunchedEffect(currentQuestionIndex) {
        // Countdown timer
        while (timeLeft > 0 && !answerShown) {
            delay(1000L) // 1 detik
            timeLeft--
        }
        if (!answerShown) {
            answerShown = true
            delay(1000L) // Tampilkan jawaban benar selama 1 detik sebelum lanjut
            goToNextQuestion()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Jumlah soal yang sudah dikerjakan
        Text(
            text = "Soal ${currentQuestionIndex + 1}/${questions.size}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Progress bar untuk countdown detik
        LinearProgressIndicator(
            progress = timeLeft / 10f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Teks pertanyaan
        Text(text = questions[currentQuestionIndex].question, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))
        questions[currentQuestionIndex].answers.forEachIndexed { index, answer ->
            AnswerButton(
                answer = answer,
                isSelected = selectedAnswerIndex == index,
                isCorrect = index == questions[currentQuestionIndex].correctAnswerIndex,
                showAnswer = answerShown,
                onClick = {
                    if (!answerShown) {
                        selectedAnswerIndex = index
                        answerShown = true
                        scope.launch {
                            delay(1000L) // Tampilkan jawaban selama 1 detik sebelum lanjut
                            goToNextQuestion()
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AnswerButton(
    answer: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showAnswer: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        showAnswer && isCorrect -> Color.Green
        showAnswer && !isCorrect && isSelected -> Color.Red
        else -> Color.LightGray
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        enabled = !showAnswer
    ) {
        Text(text = answer, fontSize = 16.sp, color = Color.White)
    }
}

data class QuestionSD(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

