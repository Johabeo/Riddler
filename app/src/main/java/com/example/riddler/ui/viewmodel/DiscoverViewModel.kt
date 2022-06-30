package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.QuizRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverViewModel @Inject constructor (val quizRepo: QuizRepository): ViewModel() {

    fun getQuiz(): Observable<List<Quiz>> {
        return getQuiz(100,0)
    }

    fun getQuiz(limit: Int, offset: Int): Observable<List<Quiz>> {
        return quizRepo.getQuizzes(limit, offset)
    }
}