package com.example.riddler.data.repo

import com.example.riddler.data.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore

interface MainRepo {
    val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

    fun insertQuiz(quiz: Quiz)
    fun getAllQuizByUser(user: String) : List<Quiz>
}