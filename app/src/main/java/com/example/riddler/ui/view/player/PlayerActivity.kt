package com.example.riddler.ui.view.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.ui.view.host.HostLobbyFragment
import com.example.riddler.ui.viewmodel.PlayerViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PlayerActivity : AppCompatActivity() {
    private val vm: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val score = findViewById<TextView>(R.id.score)
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.playerContainer, PlayerLobbyFragment())
        ft.commit()
        val pin = intent.getStringExtra("pin")
        vm.playerLobby(pin!!)

        vm.totalScore.observe(this) {
            score.text = it.toString()
        }
    }
}