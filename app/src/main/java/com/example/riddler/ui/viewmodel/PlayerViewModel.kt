package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.Global
import com.example.riddler.Util
import com.example.riddler.Util.Companion.toDataClass
import com.example.riddler.data.model.Lobby
import com.example.riddler.data.model.QuizGame
import com.google.firebase.firestore.FirebaseFirestore


import com.example.riddler.data.repo.GameRepository
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.gson.Gson

class PlayerViewModel : ViewModel() {
    val repo = GameRepository()
    var pin = MutableLiveData<String>()
    var lobbyState = MutableLiveData<Lobby>()
    var gameState = MutableLiveData<QuizGame>()
    var totalScore = MutableLiveData<Int>()
    lateinit var lobbyListener: ListenerRegistration
    lateinit var gameListener: ListenerRegistration

    init {
        totalScore.value = 0
    }

    fun callJoinLobby(gameId: String, playerName: String, joinLobby: (String) -> Unit) {
        try {
            repo.joinLobby(gameId, Global.userId, playerName)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        val e = task.exception
                        if (e is FirebaseFunctionsException) {

                            // Function error code, will be INTERNAL if the failure
                            // was not handled properly in the function call.
                            val code = e.code

                            // Arbitrary error details passed back from the function,
                            // usually a Map<String, Any>.
                            val details = e.details
                        }
                    } else {
                        joinLobby(gameId)
                    }
                }
        } catch (e: Exception) {
            println(e)
        }
    }
    fun playerLobby(gameId: String) {
        pin.value = gameId
        try {
            val docRef = FirebaseFirestore.getInstance().collection("lobby").document(gameId)
            lobbyListener = docRef.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    lobbyState.value = snapshot.data?.toDataClass()
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun joinGame() {
        //Remove listener for lobby before listening to new game state
        lobbyListener.remove()
        val docRef = FirebaseFirestore.getInstance().collection("game").document(pin.value!!)
        gameListener = docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                gameState.value = snapshot.data?.toDataClass()
            }
        }
    }

    fun submitAnswer(answer: String, moveToResultFragment: (Boolean)-> Unit) {
        repo.submitAnswer(pin.value!!, Global.userId, answer)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    if (e is FirebaseFunctionsException) {

                        // Function error code, will be INTERNAL if the failure
                        // was not handled properly in the function call.
                        val code = e.code

                        // Arbitrary error details passed back from the function,
                        // usually a Map<String, Any>.
                        val details = e.details
                    }
                } else {
                    val result = task.result
                    val isCorrect = result["isAnswerCorrect"] as Boolean
                    totalScore.value = result["totalScore"] as Int
                    moveToResultFragment(isCorrect)
                }
            }

    }
}