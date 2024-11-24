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

class QuizSMP : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppSMP()
        }
    }
}

@Composable
fun QuizAppSMP() {
    val questions = listOf(
        QuestionSMP("Seekor kambing memiliki berat badan 30 kg. Jika beratnya bertambah 15%, berapakah beratnya sekarang?", listOf("33 kg", "34.5 kg", "35 kg", "36 kg"), 1),
        QuestionSMP("Siapakah penulis novel 'Laskar Pelangi'?", listOf("Andrea Hirata", "Tere Liye", "Pramoedya Ananta Toer", "Habiburrahman El Shirazy"), 0),
        QuestionSMP("Apa rumus kimia dari garam dapur?", listOf("H2O", "CO2", "NaCl", "KCl"), 2),
        QuestionSMP("Kerajaan Hindu pertama di Indonesia adalah?", listOf("Sriwijaya", "Kutai", "Majapahit", "Tarumanegara"), 1),
        QuestionSMP("Manakah dari berikut ini yang bukan merupakan jenis energi terbarukan?", listOf("Angin", "Surya", "Minyak Bumi", "Air"), 2),
        QuestionSMP("Berapa banyak sisi yang dimiliki oleh prisma segitiga?", listOf("5", "6", "7", "8"), 1),
        QuestionSMP("Siapakah tokoh yang dikenal sebagai 'Bapak Pramuka Indonesia'?", listOf("Soedirman", "Ir. Soekarno", "Sri Sultan Hamengkubuwono IX", "Ki Hajar Dewantara"), 2),
        QuestionSMP("Apa hasil dari 12 x 8 ÷ 4 + 6?", listOf("18", "24", "36", "42"), 1),
        QuestionSMP("Choose the correct word: 'She ---- her project before the deadline.'", listOf("complete", "completed", "completes", "completing"), 1),
        QuestionSMP("Which country is known as the 'Land of the Rising Sun'?", listOf("China", "Japan", "South Korea", "India"), 1),
        QuestionSMP("Berikut ini adalah planet yang memiliki satelit alami bernama 'Titan'. Planet apakah itu?", listOf("Mars", "Saturnus", "Jupiter", "Neptunus"), 1),
        QuestionSMP("Siapakah penemu telepon?", listOf("Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Michael Faraday"), 0),
        QuestionSMP("Pancasila pertama kali dirumuskan dalam sidang apa?", listOf("Sidang BPUPKI", "Sidang PPKI", "Sidang KNIP", "Sidang Konstituante"), 0),
        QuestionSMP("Berapa banyak rusuk yang dimiliki oleh kubus?", listOf("6", "8", "10", "12"), 3),
        QuestionSMP("Apa kepanjangan dari WHO?", listOf("World Health Organization", "World Human Organization", "World Hope Organization", "World Help Organization"), 0),
        QuestionSMP("Dalam permainan badminton, berapa poin yang dibutuhkan untuk memenangkan satu game?", listOf("15", "21", "25", "30"), 1),
        QuestionSMP("Berikut ini adalah contoh bahan bakar fosil, kecuali?", listOf("Minyak Bumi", "Batubara", "Gas Alam", "Angin"), 3),
        QuestionSMP("Siapakah tokoh yang dikenal sebagai 'Bapak Pendidikan Nasional'?", listOf("Soekarno", "Mohammad Hatta", "Ki Hajar Dewantara", "Kartini"), 2),
        QuestionSMP("Choose the correct form: 'They ---- to the park every weekend.'", listOf("go", "went", "going", "goes"), 0),
        QuestionSMP("Apa nama samudra terbesar di dunia?", listOf("Samudra Hindia", "Samudra Atlantik", "Samudra Pasifik", "Samudra Arktik"), 2)
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
            AnswerButtonSMP(
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
fun AnswerButtonSMP(
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

data class QuestionSMP(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

