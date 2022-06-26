package com.example.riddler.ui.view.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.riddler.R
import com.google.firebase.firestore.FirebaseFirestore

class PlayerActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        joinGame("12")
    }


    fun joinGame(gameId: String) {
        val docRef = db.collection("quizzes").document(gameId)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                println(30)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                println(snapshot.get("Question"))
                println(snapshot.get("Answer1"))
                println(snapshot.get("Answer2"))
                println(snapshot.get("Answer3"))
                println(snapshot.get("Answer4"))
            } else {
                println(2)
            }
        }
    }
}