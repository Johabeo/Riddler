package com.example.riddler

import retrofit2.Response
import javax.inject.Inject

class TriviaRepo @Inject constructor(val inter : RetroApiInterface) {
    suspend fun getAllTriviaQuestions(amount:Int, category:Int, difficulty:String, type:String)
    = inter.getAllTriviaQuestions(amount, category, difficulty, type)


    suspend fun getAllTriviaQuestions(amount:Int, category:Int, difficulty:String): Response<TriviaQuestions>
            = inter.getAllTriviaQuestions(amount, category, difficulty)
}