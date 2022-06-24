package com.example.riddler

class TriviaRepo(val inter : RetroApiInterface) {
    suspend fun getAllTriviaQuestions(amount:Int, category:Int, difficulty:String, type:String)
    = inter.getAllTriviaQuestions(amount, category, difficulty, type)
}