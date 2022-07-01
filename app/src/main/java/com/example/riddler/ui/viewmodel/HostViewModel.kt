package com.example.riddler.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.Util
import com.example.riddler.Util.Companion.serializeToMap
import com.example.riddler.Util.Companion.toDataClass
import com.example.riddler.data.model.*
import com.example.riddler.data.repo.GameRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.gson.Gson
import timber.log.Timber
import kotlin.Exception

class HostViewModel  : ViewModel() {
    val repo = GameRepository()
    var pin = MutableLiveData<String>()
    lateinit var questionList: List<Questions>
    var currentQuestion = 0
    var lobbyState = MutableLiveData<Lobby>()
    var gameState = MutableLiveData<QuizGame>()
    lateinit var lobbyListener: ListenerRegistration
    lateinit var gameListener: ListenerRegistration

    fun callCreateLobby(lobbySize: Int) {
        // [START call_add_numbers]
        repo.createLobby(lobbySize)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    Timber.d(e)
                } else {
                    hostLobby(task.result)
                }
            }
    }

    fun hostLobby(gameId: String) {
        pin.value = gameId
        val docRef = FirebaseFirestore.getInstance().collection("lobby").document(gameId)
        lobbyListener = docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                lobbyState.value = snapshot.data?.toDataClass()
            }
        }
    }

    suspend fun startGame(loadGame: () -> Unit) {
        val gameId = pin.value
        if (gameId.isNullOrEmpty()) {
            return
        }
        try {
            repo.startGame(gameId, questionList.get(0))
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        val e = task.exception
                        Timber.d(e)
                    }
                    createGameInstance()
                    loadGame()
                }
        }catch (e : Exception) {
            Timber.d(e)
        }
    }

    fun createGameInstance() {
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

    fun callNextQuestion(nextFragment: () -> Unit) {
        try {
            val qNumber = gameState.value!!.currentQuestion!!
            if (qNumber >= questionList.size) {
                //TODO display final leaderboard/move this somewhere else
                return
            }

            repo.nextQuestion(pin.value!!, questionList.get(qNumber))
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        val e = task.exception
                        Timber.d(e)
                    } else {
                        nextFragment()
                    }
                }
        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    fun displayLeaderboard() {
        repo.displayLeaderboard(pin.value!!)
    }

    fun setCurrentQuestions(_questionList: List<Questions>) {
        questionList = _questionList
        println(questionList)
    }
}