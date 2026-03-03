package com.example.quiz_app_starter.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quiz_app_starter.ui.theme.QuizappstarterTheme

@Composable
fun AnswerCard(
    answerText: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = answerText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "AnswerCard Unselected")
@Composable
fun AnswerCardUnselectedPreview() {
    QuizappstarterTheme(darkTheme = false) {
        AnswerCard(
            answerText = "Paris",
            isSelected = false,
            onSelect = {}
        )
    }
}

@Preview(showBackground = true, name = "AnswerCard Selected")
@Composable
fun AnswerCardSelectedPreview() {
    QuizappstarterTheme(darkTheme = false) {
        AnswerCard(
            answerText = "Paris",
            isSelected = true,
            onSelect = {}
        )
    }
}

@Preview(showBackground = true, name = "AnswerCard Dark Selected")
@Composable
fun AnswerCardDarkPreview() {
    QuizappstarterTheme(darkTheme = true) {
        AnswerCard(
            answerText = "Paris",
            isSelected = true,
            onSelect = {}
        )
    }
}


