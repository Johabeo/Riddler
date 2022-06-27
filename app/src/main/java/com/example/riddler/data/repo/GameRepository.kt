package com.example.riddler.data.repo


import com.example.riddler.Util.Companion.serializeToMap
import com.example.riddler.data.model.Questions
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase

class GameRepository() {
    var functions = Firebase.functions

     fun createLobby(id: Int): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "userId" to id
        )

        return functions
            .getHttpsCallable("createLobby")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as String
                result
            }
    }


    fun joinLobby(gameId: String, playerId: Int, playerName: String): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "gameId" to gameId,
            "playerId" to playerId,
            "playerName" to playerName
        )

        return functions
            .getHttpsCallable("joinLobby")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as String
                result
            }
    }
    fun startGame(gameId: String): Task<String> {
        var question = Questions(1,1,"What is 1+1?","2",
            "3","4","5","2")
        return startGame(gameId,question)
    }
    fun startGame(gameId: String, question: Questions): Task<String>  {
        var qMap = question.serializeToMap()
        val data = hashMapOf(
            "gameId" to gameId,
            "question" to qMap
        )
        return functions
            .getHttpsCallable("startGame")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as String
                result
            }
    }

}