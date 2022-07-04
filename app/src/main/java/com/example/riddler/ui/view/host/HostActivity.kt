package com.example.riddler.ui.view.host

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.view.MainActivity
import com.example.riddler.ui.view.player.PlayerFinalLeaderboardFragment
import com.example.riddler.ui.view.player.PlayerGameFragment
import com.example.riddler.ui.view.player.PlayerLeaderboardFragment
import com.example.riddler.ui.viewmodel.DiscoverViewModel
import com.example.riddler.ui.viewmodel.HostViewModel
import com.example.riddler.ui.viewmodel.PlayerViewModel
import com.example.riddler.ui.viewmodel.QuizViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    lateinit var hostVM : HostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        val quizVM = ViewModelProvider(this).get(QuizViewModel::class.java)
        val quizId = intent.getIntExtra("quizId", 0)
        hostVM = ViewModelProvider(this).get(HostViewModel::class.java)
        hostVM.setCurrentQuestions(quizVM.getQuestions(quizId))
        val leave = findViewById<Button>(R.id.hostLeave)

        leave.setOnClickListener {
            hostVM.leave()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.hostContainer, HostLobbyFragment())
        ft.commit()
    }

}