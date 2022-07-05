package com.example.riddler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.example.riddler.ui.viewmodel.QuizViewModel

class NewQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_quiz)

        var btn = findViewById<Button>(R.id.createButton)
        btn.setOnClickListener() {
            val vm = ViewModelProvider(this).get(QuizViewModel::class.java)
            var quizTitle = findViewById<TextView>(R.id.editTextName).text as String
            var quizDesc = findViewById<TextView>(R.id.editTextDesc).text as String
            var quizType = findViewById<TextView>(R.id.editTextType).text as String
            var quiz = Quiz("asdfas", quizTitle, quizDesc, quizType)
            var id = vm.insertQuiz(quiz)

            val q1 = findViewById<View>(R.id.question1)
            vm.insertQuestion(Questions(id.toString().toInt(),
                q1.findViewById<TextView>(R.id.editTextQuestion).text as String?,
                q1.findViewById<TextView>(R.id.editTextFirstAnswer).text as String?,
                q1.findViewById<TextView>(R.id.editTextSecondAnswer).text as String?,
                q1.findViewById<TextView>(R.id.editTextThirdAnswer).text as String?,
                q1.findViewById<TextView>(R.id.editTextFourthAnswer).text as String?,
                q1.findViewById<TextView>(R.id.editTextCorrectAnswer).text as String?
            ))

            val q2 = findViewById<View>(R.id.question2)
            vm.insertQuestion(Questions(id.toString().toInt(),
                q2.findViewById<TextView>(R.id.editTextQuestion).text as String?,
                q2.findViewById<TextView>(R.id.editTextFirstAnswer).text as String?,
                q2.findViewById<TextView>(R.id.editTextSecondAnswer).text as String?,
                q2.findViewById<TextView>(R.id.editTextThirdAnswer).text as String?,
                q2.findViewById<TextView>(R.id.editTextFourthAnswer).text as String?,
                q2.findViewById<TextView>(R.id.editTextCorrectAnswer).text as String?
            ))

            val q3 = findViewById<View>(R.id.question3)
            vm.insertQuestion(Questions(id.toString().toInt(),
                q3.findViewById<TextView>(R.id.editTextQuestion).text as String?,
                q3.findViewById<TextView>(R.id.editTextFirstAnswer).text as String?,
                q3.findViewById<TextView>(R.id.editTextSecondAnswer).text as String?,
                q3.findViewById<TextView>(R.id.editTextThirdAnswer).text as String?,
                q3.findViewById<TextView>(R.id.editTextFourthAnswer).text as String?,
                q3.findViewById<TextView>(R.id.editTextCorrectAnswer).text as String?
            ))

            val q4 = findViewById<View>(R.id.question4)
            vm.insertQuestion(Questions(id.toString().toInt(),
                q4.findViewById<TextView>(R.id.editTextQuestion).text as String?,
                q4.findViewById<TextView>(R.id.editTextFirstAnswer).text as String?,
                q4.findViewById<TextView>(R.id.editTextSecondAnswer).text as String?,
                q4.findViewById<TextView>(R.id.editTextThirdAnswer).text as String?,
                q4.findViewById<TextView>(R.id.editTextFourthAnswer).text as String?,
                q4.findViewById<TextView>(R.id.editTextCorrectAnswer).text as String?
            ))

            val q5 = findViewById<View>(R.id.question5)
            vm.insertQuestion(Questions(id.toString().toInt(),
                q5.findViewById<TextView>(R.id.editTextQuestion).text as String?,
                q5.findViewById<TextView>(R.id.editTextFirstAnswer).text as String?,
                q5.findViewById<TextView>(R.id.editTextSecondAnswer).text as String?,
                q5.findViewById<TextView>(R.id.editTextThirdAnswer).text as String?,
                q5.findViewById<TextView>(R.id.editTextFourthAnswer).text as String?,
                q5.findViewById<TextView>(R.id.editTextCorrectAnswer).text as String?
            ))
        }

    }
}