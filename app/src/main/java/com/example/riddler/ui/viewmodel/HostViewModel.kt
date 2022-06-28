package com.example.riddler.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.Util
import com.example.riddler.Util.Companion.serializeToMap
import com.example.riddler.Util.Companion.toDataClass
import com.example.riddler.data.model.Lobby
import com.example.riddler.data.model.LobbyPlayers
import com.example.riddler.data.model.QuizGame
import com.example.riddler.data.repo.GameRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.gson.Gson
import java.lang.Exception

class HostViewModel  : ViewModel() {
    val repo = GameRepository()
    var pin = MutableLiveData<String>()
    var lobbyState = MutableLiveData<Lobby>()
    var gameState = MutableLiveData<QuizGame>()
    lateinit var lobbyListener: ListenerRegistration
    lateinit var gameListener: ListenerRegistration

    fun callCreateLobby(id: Int, lobbySize: Int) {
        // [START call_add_numbers]
        repo.createLobby(id, lobbySize)
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
                    hostLobby(task.result)
                }
            }
        // [END call_add_numbers]
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
    fun callStartGame(gameId: String) {

    }

    suspend fun startGame(loadGame: () -> Unit) {
        val gameId = pin.value
        if (gameId.isNullOrEmpty()) {
            return
        }
        try {
            repo.startGame(gameId)
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
                    }
                    createGameInstance()
                    loadGame()
                }
        }catch (e : Exception) {
            println(e)
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
}