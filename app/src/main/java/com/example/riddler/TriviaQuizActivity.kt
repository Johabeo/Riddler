package com.example.riddler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TriviaQuizActivity : AppCompatActivity() {

    lateinit var vm: ApiViewModel
    var amount: Int = 0 //10
    var category: Int = 0 //9
    var difficulty: String = "" //easy
    var questionList = ArrayList<TriviaQuestions.Question>()
    lateinit var triviaAdapter: TriviaQuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_quiz)

        val inter = RetroApiInterface.create()
        val repo = TriviaRepo(inter)
        vm = ApiViewModel(repo)

        var extras: Bundle? = getIntent().getExtras()
        if (extras != null) {
            amount = extras.getInt("amount")
            category = extras.getInt("category")
            difficulty = extras.getString("difficulty").toString()
        }

        vm.getAllTriviaQuestions(amount, category, difficulty, "multiple")
        vm.triviaQuestions.observe(this) {
            println(it)
            questionList = QuestionListGenerator().makeList(it)
            triviaAdapter.setItems(questionList)
        }

        triviaAdapter = TriviaQuizAdapter(questionList)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = triviaAdapter

    }
}