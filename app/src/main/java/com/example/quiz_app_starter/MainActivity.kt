package com.example.quiz_app_starter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app_starter.ui.theme.GoldAccent
import com.example.quiz_app_starter.ui.theme.QuizappstarterTheme

private const val TAG = "MainMenuScreen"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizappstarterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainMenuScreen(
                        bestScore = 42,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen(
    bestScore: Int = 0,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Custom quiz logo
        Image(
            painter = painterResource(id = R.drawable.quiz_logo),
            contentDescription = "Quiz App Logo",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "QuizApp",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        // buildAnnotatedString for formatted subtitle
        Text(
            text = buildAnnotatedString {
                append("Test your ")
                withStyle(
                    style = SpanStyle(
                        color = GoldAccent,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                ) {
                    append("knowledge")
                }
                append("!\n")
                withStyle(
                    style = SpanStyle(
                        fontStyle = FontStyle.Italic,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    append("How well do you really know it?")
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🏆  Best of all time",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = bestScore.toString(),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldAccent
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Log.d(TAG, "Play! button clicked — starting a new quiz session")
            },
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Play!",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, name = "MainMenuPreview Light")
@Composable
fun MainMenuScreenPreview() {
    QuizappstarterTheme(darkTheme = false) {
        MainMenuScreen(42, Modifier)
    }
}

@Preview(showBackground = true, name = "MainMenuPreview Dark")
@Composable
fun MainMenuScreenPreviewDark() {
    QuizappstarterTheme(darkTheme = true) {
        MainMenuScreen(42, Modifier)
    }
}