package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.Util.Companion.toDataClass
import com.example.riddler.data.model.Lobby
import com.example.riddler.data.model.QuizGame
import com.google.firebase.firestore.FirebaseFirestore


import com.example.riddler.data.repo.GameRepository
import com.google.firebase.firestore.ListenerRegistration
import timber.log.Timber

class PlayerViewModel : ViewModel() {
    val repo = GameRepository()
    var pin = MutableLiveData<String>()
    var lobbyState = MutableLiveData<Lobby>()
    var gameState = MutableLiveData<QuizGame>()
    var totalScore = MutableLiveData<Int>()
    var currentQuestionNumber = 0
    var lobbyType = "lobby"
    lateinit var lobbyListener: ListenerRegistration
    lateinit var gameListener: ListenerRegistration

    init {
        totalScore.value = 0
    }

    fun callJoinLobby(gameId: String, joinLobby: (String, Boolean) -> Unit) {
        repo.joinLobby(gameId)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    Timber.d(e)
                    joinLobby(gameId,false)
                } else {
                    joinLobby(gameId,task.result)
                }
            }
    }
    fun playerLobby(gameId: String) {
        pin.value = gameId
        val docRef = FirebaseFirestore.getInstance().collection("lobby").document(gameId)
        lobbyListener = docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Timber.d(e)
            }

            if (snapshot != null && snapshot.exists()) {
                lobbyState.value = snapshot.data?.toDataClass()
            }
        }
    }

    fun joinGame() {
        //Remove listener for lobby before listening to new game state
        lobbyListener.remove()
        val docRef = FirebaseFirestore.getInstance().collection("game").document(pin.value!!)
        gameListener = docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Timber.d(e)
            }
            if (snapshot != null && snapshot.exists()) {
                gameState.value = snapshot.data?.toDataClass()
            }
        }
        repo.leaveLobby(pin.value!!,lobbyType)
        lobbyType = "game"
    }

    fun submitAnswer(answer: String, moveToResultFragment: (Boolean)-> Unit) {
        repo.submitAnswer(pin.value!!, answer)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    Timber.d(e)
                } else {
                    val result = task.result
                    val isCorrect = result["isAnswerCorrect"] as Boolean
                    totalScore.value = result["totalScore"] as Int
                    moveToResultFragment(isCorrect)
                }
            }

    }

    fun checkQuestion(updatedQuestionNumber: Int): Boolean{
        if (updatedQuestionNumber > currentQuestionNumber) {
            currentQuestionNumber = updatedQuestionNumber
            return true
        } else {
            return false
        }
    }

    fun leave() {
        pin.value?.let {
            repo.leaveLobby(it,"lobby")
        }
    }
}