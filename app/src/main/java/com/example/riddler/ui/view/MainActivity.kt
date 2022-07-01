package com.example.riddler.ui.view


import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.riddler.R
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.ui.view.player.PlayerJoinLobbyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.functions.FirebaseFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        setFragment(DiscoverFragment())
        val gr = GameRepository()
        //gr.startGame("QWYuoR2qmbFMMUimLp07")
        //gr.submitAnswer("QWYuoR2qmbFMMUimLp07",1,"2",30)
        val menuBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        val mOnNavigationItemSelectedListener=
            NavigationBarView.OnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.join -> {
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
    fun setFragment(fragment : Fragment) {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

    }

}