package com.example.riddler.ui.view.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.riddler.R
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.viewmodel.PlayerViewModel
import com.google.firebase.firestore.FirebaseFirestore

class HostActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)




    }

    fun createRoom() {

    }
}