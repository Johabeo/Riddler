package com.example.riddler.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.riddler.Global
import com.example.riddler.TriviaQuestions
import com.example.riddler.TriviaRepo
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(val repo: TriviaRepo, val quizRepo: QuizRepository): ViewModel()  {



    fun createQuizFromApi(amount: Int, category: Int, difficulty: String, quiz: Quiz) {
        CoroutineScope(Dispatchers.IO).launch {
            var res= repo.getAllTriviaQuestions(amount, category, difficulty)
            if (res.isSuccessful) {
               res.body()?.results?.let {
                   insertQuestions(it, quiz)
               }
            }
        }
    }

    fun insertQuestions(triviaQuestions: List<TriviaQuestions.Question>, quiz: Quiz) {
        var questionList = ArrayList<Questions>()
        val id = quizRepo.insertQuiz(quiz)
        for (q in triviaQuestions) {
            var answerList = q.incorrect_answers.toMutableList()
            if (answerList.size < 3)
                return
            answerList.add(q.correct_answer)
            answerList.shuffle()
            var question = Questions(id.toInt(),q.question,answerList[0],answerList[1],answerList[2],answerList[3], q.correct_answer)
            questionList.add(question)
        }

        for (q in questionList) {
            quizRepo.insertQuestions(q)
            println(q)
        }

    }

    fun getQuestions(quizId: Int) : List<Questions> {
        return quizRepo.getQuizQuestion(quizId)
    }
}