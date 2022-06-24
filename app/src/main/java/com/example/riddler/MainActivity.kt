package com.example.riddler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.RoomDatabase
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.model.FavoriteQuiz
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.QuizRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var functions: FirebaseFunctions
    var quizList = ArrayList<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}