package com.example.riddler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var functions: FirebaseFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        val question1 = Questions("1+1","1","2","3","4","2")
        val question2 = Questions("1+2","1","2","3","4","3")
        val sampleQuiz = Quiz("test", "xuan", "sample algebra questions", "math", listOf(question1, question2))
        val fr = FirestoreRepository()
        fr.insertQuiz(sampleQuiz)
        println(fr.getAllQuizByUser("xuan"))

    }

}