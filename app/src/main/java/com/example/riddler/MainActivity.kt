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

        val dao = AppDatabase.getInstance(this)?.quizDao()!!
        val repo = QuizRepository(dao)

        val sampleQuiz = Quiz(1,1,"test", "sample algebra questions", "math")
        val favoriteQuiz = FavoriteQuiz(1,1,1)
        val question1 = Questions(1,1,"1+1","1","2","3","4","2")
        val question2 = Questions(2,1,"1+2","1","2","3","4","3")
        AppDatabase.clearTable()

        //just testing, no vm for now
        repo.insertQuiz(sampleQuiz)
        repo.insertFavoriteQuiz(favoriteQuiz)
        repo.insertQuestions(question1)
        repo.insertQuestions(question2)
        repo.getFavoriteQuizzes(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    println(it)
                },
            )






//        val db = FirebaseFirestore.getInstance()
//        val question1 = Questions("1+1","1","2","3","4","2")
//        val question2 = Questions("1+2","1","2","3","4","3")
//        val sampleQuiz = Quiz("test", "xuan", "sample algebra questions", "math", listOf(question1, question2))
//        val fr = FirestoreRepository()
//        fr.insertQuiz(sampleQuiz)
//        println(fr.getAllQuizByUser("xuan"))

    }

}