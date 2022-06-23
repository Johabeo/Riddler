package com.example.riddler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ApiViewModel(val repo: TriviaRepo) : ViewModel() {
    var triviaQuestions = MutableLiveData<TriviaQuestions>()
    var job : Job? = null

    fun getAllTriviaQuestions(amount:Int, category:Int, difficulty:String, type:String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllTriviaQuestions(amount, category, difficulty, type)
            if (res.isSuccessful) {
                triviaQuestions!!.postValue(res.body())
            }
        }
    }

}