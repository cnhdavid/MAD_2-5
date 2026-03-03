package com.example.quiz_app_starter.model

data class Question(
    val id: Int,
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

fun getDummyQuestions(): List<Question> = listOf(
    Question(
        id = 1,
        text = "What is the capital of France?",
        answers = listOf("Berlin", "Madrid", "Paris", "Rome"),
        correctAnswerIndex = 2
    ),
    Question(
        id = 2,
        text = "Which planet is known as the Red Planet?",
        answers = listOf("Venus", "Mars", "Jupiter", "Saturn"),
        correctAnswerIndex = 1
    ),
    Question(
        id = 3,
        text = "What is the largest ocean on Earth?",
        answers = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
        correctAnswerIndex = 3
    ),
    Question(
        id = 4,
        text = "Who wrote the play 'Romeo and Juliet'?",
        answers = listOf("Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"),
        correctAnswerIndex = 1
    ),
    Question(
        id = 5,
        text = "What is the chemical symbol for Gold?",
        answers = listOf("Go", "Gd", "Au", "Ag"),
        correctAnswerIndex = 2
    )
)

