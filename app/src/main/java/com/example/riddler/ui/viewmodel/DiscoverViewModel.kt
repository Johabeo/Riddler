package com.example.riddler.ui.viewmodel

import android.graphics.pdf.PdfDocument
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.QuizRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverViewModel @Inject constructor (val quizRepo: QuizRepository,
                        val firebaseRepository: FirestoreRepository): ViewModel() {

    val userProfile = firebaseRepository.userProfile
    var query = ""
    var page = 1
    val OFFSET = 0
    val LIMIT = 10

    fun getQuiz(): Observable<List<Quiz>> {
        page = 1
        return getQuiz(LIMIT*page,OFFSET)
    }
    fun getQuiz(limit: Int, offset: Int): Observable<List<Quiz>> {
        query = ""
        return quizRepo.getQuizzes(limit, offset)
    }

    fun isUserLoggedIn() : Boolean {
        return firebaseRepository.isUserLoggedIn()
    }

    fun fetchUserProfileInfo(){
        firebaseRepository.fetchUserProfileInfo()
    }

    fun searchQuiz(searchQuery: String): Observable<List<Quiz>> {
        page = 1
        query = searchQuery
        return quizRepo.search(searchQuery, LIMIT*page, OFFSET)
    }

    fun loadMore(): Observable<List<Quiz>> {
        page++
        if (query == "") {
            return quizRepo.getQuizzes(LIMIT*page,OFFSET)
        } else {
            return quizRepo.search(query, LIMIT*page, OFFSET)
        }

    }
}