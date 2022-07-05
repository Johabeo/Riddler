package com.example.riddler.data.repo


import com.example.riddler.Util.Companion.serializeToMap
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.UserProfile
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.HttpsCallableResult
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase

class GameRepository() {
    var functions = Firebase.functions
    var user = Firebase.auth
    fun createLobby(lobbySize: Int): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "userId" to user.uid,
            "lobbySize" to lobbySize
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

    fun joinLobby(gameId: String): Task<Boolean> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "gameId" to gameId,
            "playerId" to user.uid,
            "playerName" to user.currentUser?.displayName
        )

        return functions
            .getHttpsCallable("joinLobby")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as Boolean
                result
            }
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

    fun finishGame(gameId: String) {
        val data = hashMapOf(
            "gameId" to gameId,
        )
        functions
            .getHttpsCallable("finishGame")
            .call(data)
    }

    fun submitAnswer(gameId: String, answer: String): Task<HashMap<String,Any>>  {
        val data = hashMapOf(
            "gameId" to gameId,
            "playerId" to user.uid,
            "answer" to answer
        )
        return functions
            .getHttpsCallable("submitAnswer")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down
                val result = task.result?.data as HashMap<String,Any>
                result
            }
    }

    fun nextQuestion(gameId: String, question: Questions): Task<Boolean> {
        var qMap = question.serializeToMap()
        val data = hashMapOf(
            "gameId" to gameId,
            "question" to qMap
        )

        return functions
            .getHttpsCallable("nextQuestion")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as Boolean
                result
            }
    }

    fun displayLeaderboard(gameId: String) {
        val data = hashMapOf(
            "gameId" to gameId
        )

        functions
            .getHttpsCallable("displayLeaderboard")
            .call(data)
    }

    //lobbyType is either game or lobby
    fun leaveLobby(gameId: String, lobbyType: String) {
        val data = hashMapOf(
            "gameId" to gameId,
            "playerId" to user.uid,
            "lobbyType" to lobbyType
        )
        functions
            .getHttpsCallable("leaveLobby")
            .call(data)
    }

    fun hostLeave(gameId: String, lobbyType: String) {
        val data = hashMapOf(
            "gameId" to gameId,
            "lobbyType" to lobbyType
        )
        functions
            .getHttpsCallable("hostLeave")
            .call(data)
    }
}