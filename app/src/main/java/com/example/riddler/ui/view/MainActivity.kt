package com.example.riddler.ui.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.OnboardActivity
import com.example.riddler.R
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.view.dashboard.StartGameFragment
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.view.host.HostLobbyFragment
import com.example.riddler.ui.view.player.PlayerActivity
import com.example.riddler.ui.view.player.PlayerJoinLobbyFragment
import com.example.riddler.ui.view.settings.SettingsActivity
import com.example.riddler.ui.viewmodel.QuizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.functions.FirebaseFunctions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var functions: FirebaseFunctions

    @Inject
    lateinit var repository: FirestoreRepository

    var quizList = ArrayList<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(DiscoverFragment())
        val gr = GameRepository()
        //gr.startGame("QWYuoR2qmbFMMUimLp07")
        //gr.submitAnswer("QWYuoR2qmbFMMUimLp07",1,"2",30)
        val menuBar: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val mOnNavigationItemSelectedListener =
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.play -> {
                        setFragment(PlayerJoinLobbyFragment())
                    }

                    R.id.create -> {
                        setFragment(CreateQuizFragment())
                    }

                    R.id.discover -> {
                        setFragment(DiscoverFragment())
                    }
                }
                false
            }
        menuBar.setOnItemSelectedListener(mOnNavigationItemSelectedListener)

        //println(TriviaRepo(RetroApiInterface.create()).getAllTriviaQuestions(10,1,"hard"))
    }

    fun setFragment(fragment: Fragment) {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

    }

    fun openHostActivity() {
        intent = Intent(this, HostActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun openPlayerActivity() {
        intent = Intent(this, PlayerActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*if(item.itemId == R.id.toolbar_settings){
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }*/
        when (item.itemId) {
            R.id.toolbar_settings -> {
                intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }

            R.id.toolbar_logout -> {
                signOut()
            }
        }

        return true
    }

    fun signOut() {
        repository.auth.signOut()
        intent = Intent(this, OnboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}