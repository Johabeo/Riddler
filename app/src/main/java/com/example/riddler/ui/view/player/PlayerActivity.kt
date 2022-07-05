package com.example.riddler.ui.view.player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.TriviaQuizActivity
import com.example.riddler.ui.view.CreateQuizFragment
import com.example.riddler.ui.view.DiscoverFragment
import com.example.riddler.ui.view.MainActivity
import com.example.riddler.ui.view.host.HostLobbyFragment
import com.example.riddler.ui.viewmodel.PlayerViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.firestore.FirebaseFirestore

class PlayerActivity : AppCompatActivity() {
    private val vm: PlayerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val score = findViewById<TextView>(R.id.score)
        val pin = intent.getStringExtra("pin")
        vm.playerLobby(pin!!)

        switchFragment(PlayerLobbyFragment())
        vm.totalScore.observe(this) {
            score.text = it.toString()
        }
        switchFragment(PlayerLobbyFragment())

        setSupportActionBar(findViewById(R.id.player_toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
            title = ""
        }

        vm.gameState.observe(this) {
            when(it.finished) {
                true ->  {
                    vm.leave()
                    switchFragment(PlayerFinalLeaderboardFragment())}
                else -> {}
            }
            when(it.displayingLeaderboard) {
                true -> { switchFragment(PlayerLeaderboardFragment())}
                else -> {}
            }
            when(it.hostQuit) {
                true ->  {
                    vm.leave()
                    Toast.makeText(this, "The host has disbanded the lobby", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else -> {}
            }
            it.currentQuestion?.let {
                if (vm.checkQuestion(it))
                    switchFragment(PlayerGameFragment())
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                vm.leave()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun switchFragment(fragment: Fragment) {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.playerContainer, fragment)
        ft.commit()
    }
}