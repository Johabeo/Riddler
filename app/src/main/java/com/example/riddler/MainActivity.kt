package com.example.riddler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.view.host.HostCreateLobbyFragment
import com.example.riddler.ui.view.player.PlayerJoinLobbyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.functions.FirebaseFunctions

class MainActivity : AppCompatActivity() {
    private lateinit var functions: FirebaseFunctions
    var quizList = ArrayList<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, NewTriviaActivity::class.java)
//        //uncomment if you want to open NewTriviaActivity immediately
//        startActivity(intent)

        //throw RuntimeException("testing crashlytics")

//        val intent = Intent(this, HostActivity::class.java)
////        //uncomment if you want to open NewTriviaActivity immediately
//        startActivity(intent)

        val menuBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        val mOnNavigationItemSelectedListener=
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.host -> {
                        setFragment(HostCreateLobbyFragment())
                    }
                    R.id.join -> {
                        setFragment(PlayerJoinLobbyFragment())
                    }
                }
                false
            }
        menuBar.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    fun setFragment(fragment : Fragment) {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

    }

}