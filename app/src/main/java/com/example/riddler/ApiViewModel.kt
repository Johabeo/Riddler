package com.example.riddler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.toLiveData
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
            res.onSuccess {
                triviaQuestions!!.postValue(res.getOrNull())
            }
        }
    }

}