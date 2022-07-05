package com.example.riddler.data.repo

import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class QuizRepository @Inject constructor(private val dao: QuizDao) {

//    fun getFavoriteQuizzes(user: Int): Observable<List<Quiz>> {
//        return dao.getFavoriteQuizzes(user)
//    }

    fun getMyQuizzes(user: Int): Observable<List<Quiz>> {
        return dao.getMyQuizzes(user)
    }

    fun getQuizzes(limit: Int, offset: Int): Observable<List<Quiz>> {
        return dao.getQuizzes(limit, offset)
    }

    fun getQuizQuestion(quizId: Int): List<Questions> {
        return dao.getQuizQuestions(quizId)
    }

    fun insertQuiz(quiz: Quiz): Long {
        return dao.insertQuiz(quiz)
    }

//    fun insertFavoriteQuiz(favoriteQuiz: FavoriteQuiz) {
//        dao.insertFavoriteQuiz(favoriteQuiz)
//    }

    //A quiz with the corresponding id must be inserted first
    fun insertQuestions(questions: Questions) {
        dao.insertQuestions(questions)
    }

    fun search(searchQuery : String) : Observable<List<Quiz>> {
        return dao.search("*$searchQuery*")
    }
}