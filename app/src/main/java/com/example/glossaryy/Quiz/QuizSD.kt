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
            QuizAppSD()
        }
    }
}

@Composable
fun QuizAppSD() {
    val questions = listOf(
        QuestionSD("Sebuah tabung memiliki jari-jari 7 cm dan tinggi 10 cm. Berapakah volume tabung tersebut?", listOf("1540 cm3", "1352 cm3", "1232 cm3", "1496 cm3"), 0),
        QuestionSD("Jika luas persegi adalah 144cm2, berapakah panjang sisinya?", listOf("10cm", "12cm", "14cm", "16cm"), 1),
        QuestionSD("Planet manakah yang paling dekat dengan matahari?", listOf("Venus", "Mars", "Merkurius", "Bumi"), 2),
        QuestionSD("Manakah dibawah ini yang merupakan sumber energi terbarukan?", listOf("Batubara", "Minyak Bumi", "Angin", "Gas Alam"), 2),
        QuestionSD("Siapakah proklamator Kemerdekaan Indonesia?", listOf("Mohammad Hatta dan Soekarno", "Soeharto dan Soekarno", "Mohammad Hatta dan Soeharto", "Soekarno dan Jusuf Kalla"), 0)
    )

    val randomQuestions = remember { questions.shuffled().take(5) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var answerShown by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    var score by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }
    var incorrectAnswers by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun goToNextQuestion() {
        if (currentQuestionIndex < randomQuestions.size - 1) {
            currentQuestionIndex++
            selectedAnswerIndex = null
            answerShown = false
            timeLeft = 10
        } else {
            isQuizFinished = true
        }
    }

    LaunchedEffect(currentQuestionIndex) {
        while (timeLeft > 0 && !answerShown) {
            delay(1000L)
            timeLeft--
        }
        if (!answerShown) {
            answerShown = true
            incorrectAnswers++
            delay(1000L)
            goToNextQuestion()
        }
    }

    if (isQuizFinished) {
        ResultScreen(score, correctAnswers, incorrectAnswers)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Soal ${currentQuestionIndex + 1}/${randomQuestions.size}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = timeLeft / 10f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = randomQuestions[currentQuestionIndex].question, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))

            randomQuestions[currentQuestionIndex].answers.forEachIndexed { index, answer ->
                AnswerButtonSD(
                    answer = answer,
                    isSelected = selectedAnswerIndex == index,
                    isCorrect = index == randomQuestions[currentQuestionIndex].correctAnswerIndex,
                    showAnswer = answerShown,
                    onClick = {
                        if (!answerShown) {
                            selectedAnswerIndex = index
                            answerShown = true
                            if (index == randomQuestions[currentQuestionIndex].correctAnswerIndex) {
                                score += 10
                                correctAnswers++
                            } else {
                                incorrectAnswers++
                            }
                            scope.launch {
                                delay(1000L)
                                goToNextQuestion()
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, correctAnswers: Int, incorrectAnswers: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9C27B0)) // Background ungu
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GLOSSARY",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color(0xFFD1C4E9), shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$score",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Green, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Benar", fontSize = 18.sp, color = Color.White)
                    Text("$correctAnswers", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Box(
                modifier = Modifier
                    .background(Color.Red, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Salah", fontSize = 18.sp, color = Color.White)
                    Text("$incorrectAnswers", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* Logika level berikutnya */ }) {
            Text("Level Selanjutnya")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Logika halaman peringkat */ }) {
            Text("Lihat Halaman Peringkat")
        }
    }
}

@Composable
fun AnswerButtonSD(
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
        modifier = Modifier.fillMaxWidth().background(backgroundColor),
        enabled = !showAnswer
    ) {
        Text(answer, fontSize = 16.sp, color = Color.White)
    }
}

data class QuestionSD(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
