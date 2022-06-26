package com.example.riddler.data.repo


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

    fun startGame() {
        functions
            .getHttpsCallable("startGame")
            .call()
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


}