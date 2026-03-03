package com.example.quiz_app_starter.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app_starter.model.Question
import com.example.quiz_app_starter.model.getDummyQuestions
import com.example.quiz_app_starter.ui.theme.GoldAccent
import com.example.quiz_app_starter.ui.theme.QuizappstarterTheme
import kotlinx.coroutines.delay

private const val COUNTDOWN_SECONDS = 30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    questions: List<Question> = getDummyQuestions(),
    currentQuestionIndex: Int = 0,
    onSubmit: (selectedIndex: Int) -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val question = questions[currentQuestionIndex]
    var selectedAnswerIndex by remember(currentQuestionIndex) { mutableStateOf<Int?>(null) }
    var timeLeft by remember(currentQuestionIndex) { mutableIntStateOf(COUNTDOWN_SECONDS) }

    // Countdown timer — resets each time the question changes
    LaunchedEffect(currentQuestionIndex) {
        timeLeft = COUNTDOWN_SECONDS
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val timerProgress = timeLeft / COUNTDOWN_SECONDS.toFloat()
    val timerColor by animateColorAsState(
        targetValue = when {
            timerProgress > 0.5f -> MaterialTheme.colorScheme.primary
            timerProgress > 0.25f -> GoldAccent
            else -> MaterialTheme.colorScheme.error
        },
        animationSpec = tween(durationMillis = 500),
        label = "timerColor"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Question ${currentQuestionIndex + 1} / ${questions.size}",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Text(
                        text = "⏱ $timeLeft s",
                        color = timerColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logout button
                    OutlinedButton(
                        onClick = onLogout,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Text(text = "Logout")
                    }

                    // Submit button
                    Button(
                        onClick = { onSubmit(selectedAnswerIndex ?: -1) },
                        enabled = selectedAnswerIndex != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Submit",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            // Linear progress indicator — progress through all questions
            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1) / questions.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Question card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = question.text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Choose your answer:",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Answers in a LazyColumn
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                itemsIndexed(question.answers) { index, answer ->
                    AnswerCard(
                        answerText = answer,
                        isSelected = selectedAnswerIndex == index,
                        onSelect = { selectedAnswerIndex = index }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "QuestionScreen Light")
@Composable
fun QuestionScreenPreview() {
    QuizappstarterTheme(darkTheme = false) {
        QuestionScreen()
    }
}

@Preview(showBackground = true, name = "QuestionScreen Dark")
@Composable
fun QuestionScreenPreviewDark() {
    QuizappstarterTheme(darkTheme = true) {
        QuestionScreen()
    }
}



