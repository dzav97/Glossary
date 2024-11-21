//package com.example.glossaryy.Quiz
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.glossaryy.ui.theme.GlossaryyTheme
//import kotlinx.coroutines.delay
//
//class QuizSMP : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            GlossaryyTheme { }
//            @Composable
//            fun QuizApp() {
//                val questions = listOf(
//                    Question(
//                        "Sebuah tabung memiliki jari-jari 7 cm dan tinggi 10 cm. Berapakah volume tabung tersebut?",
//                        listOf("1540 cm3", "1352 cm3", "1232 cm3", "1496 cm3"),
//                        0
//                    ),
//                    Question(
//                        "Jika luas persegi adalah 144cm2, berapakah penjang sisinya?",
//                        listOf("10cm", "12cm", "14cm", "16cm"),
//                        1
//                    ),
//                    Question(
//                        "Planet manakah yang paling dekat dengan matahari?",
//                        listOf("venus", "Mars", "Merkurius", "Bumi",),
//                        2
//                    ),
//                    Question(
//                        "Manakah dibawah ini yang merupakan sumber energi terbarukan?",
//                        listOf("Batubara", "Minyak Bumi", "Angin", "Gas Alam"),
//                        2
//                    ),
//                    Question(
//                        "Siapakah proklamator Kemerdekaan Indonesia?",
//                        listOf(
//                            "Mohammad Hattadan Soekarno",
//                            "Soeharto dan Soekarno",
//                            "Mohammad Hatta dan Soeharto",
//                            "Soekarno dan Jusuf Kalla"
//                        ),
//                        0
//                    ),
//                    Question(
//                        "Apa Nama Ibu Kota Indonesia?",
//                        listOf("Surabaya", "Medan", "Bali", "Jakarta"),
//                        3
//                    ),
//                    Question(
//                        "Apa yang dimaksud dengan sinonim?",
//                        listOf(
//                            "Kata yang berlawanan makna",
//                            "Kata yang sama makna",
//                            "Kata yang hampir punah",
//                            "Kata yang sering digunakan"
//                        ),
//                        1
//                    ),
//                    Question(
//                        "Cermati kalimat berikut: 'Ani sedang membaca buku ditaman.' Kata 'di taman' dalam kaliimat tersebut merupakan jenis keterangan apa?",
//                        listOf(
//                            "Keterangan tempat",
//                            "Keterangna Waktu",
//                            "Keterangan cara",
//                            "Keterangan alasan"
//                        ), 0
//                    ),
//                    Question(
//                        "Choose the correct form of the verb: 'She ---- to market yesterday",
//                        listOf("go", "went", "goes", "Going"),
//                        1
//                    ),
//                    Question(
//                        "What is the plural of 'child' ",
//                        listOf("Childs", "Children", "Childes", "Childeren"),
//                        1
//                    )
//                )
//                // Tambahkan lebih banyak pertanyaan di sini...
//
//
//                var currentQuestionIndex by remember { mutableStateOf(0) }
//                var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
//                var timeLeft by remember { mutableStateOf(20) }
//                var answerShown by remember { mutableStateOf(false) }
//
//                LaunchedEffect(currentQuestionIndex) {
//                    for (i in 20 downTo 1) {
//                        delay(1000L)
//                        timeLeft = i
//                    }
//                    answerShown = true
//                    delay(5000L)
//                    if (currentQuestionIndex < questions.size - 1) {
//                        currentQuestionIndex++
//                        selectedAnswerIndex = null
//                        answerShown = false
//                        timeLeft = 20
//                    }
//                }
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = "Kuis SD", fontSize = 30.sp, fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = "Pertanyaan ${currentQuestionIndex + 1} dari ${questions.size}")
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    ProgressBar(progress = (currentQuestionIndex + 1).toFloat() / questions.size)
//
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = questions[currentQuestionIndex].question, fontSize = 20.sp)
//
//                    Spacer(modifier = Modifier.height(16.dp))
//                    questions[currentQuestionIndex].answers.forEachIndexed { index, answer ->
//                        AnswerButton(
//                            answer = answer,
//                            isSelected = selectedAnswerIndex == index,
//                            isCorrect = index == questions[currentQuestionIndex].correctAnswerIndex,
//                            showAnswer = answerShown,
//                            onClick = { selectedAnswerIndex = index }
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = "Waktu Tersisa: $timeLeft detik")
//                }
//            }
//
//            @Composable
//            fun ProgressBar(progress: Float) {
//                LinearProgressIndicator(
//                    progress = progress,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(8.dp)
//                )
//            }
//
//            @Composable
//            fun AnswerButton(
//                answer: String,
//                isSelected: Boolean,
//                isCorrect: Boolean,
//                showAnswer: Boolean,
//                onClick: () -> Unit
//            ) {
//                val backgroundColor = when {
//                    showAnswer && isCorrect -> Color.Green
//                    showAnswer && !isCorrect && isSelected -> Color.Red
//                    else -> Color.LightGray
//                }
//
//                Button(
//                    onClick = onClick,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(backgroundColor),
//                    enabled = !showAnswer
//                ) {
//                    Text(text = answer, fontSize = 16.sp)
//                }
//            }
//
//            data class Question(
//                val question: String,
//                val answers: List<String>,
//                val correctAnswerIndex: Int
//            )
//        }
//    }
//}
