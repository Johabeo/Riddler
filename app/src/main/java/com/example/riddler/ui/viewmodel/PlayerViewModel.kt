package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore


import com.example.riddler.data.repo.GameRepository
import com.google.firebase.functions.FirebaseFunctionsException

class PlayerViewModel(val repo: GameRepository) {
    var playerList = MutableLiveData<List<String>>()
    fun callJoinLobby(gameId: String, playerId: Int, playerName: String) {
        // [START call_add_numbers]
        repo.joinLobby(gameId, playerId, playerName)
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
                    playerLobby()
                }
            }
        // [END call_add_numbers]
    }
    fun playerLobby() {
        println("I'm in the lobby")
    }
}