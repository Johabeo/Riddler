package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.riddler.data.repo.GameRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctionsException

class HostViewModel(val repo: GameRepository) {
    var pin = MutableLiveData<String>()
    var playerList = MutableLiveData<List<String>>()
    fun callCreateLobby(id: Int) {
        // [START call_add_numbers]
        repo.createLobby(id)
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
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                snapshot.get("players")
            } else {
                println(2)
            }
        }
    }

    fun startGame() {

    }
}