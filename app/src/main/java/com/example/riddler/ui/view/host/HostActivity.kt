package com.example.riddler.ui.view.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.riddler.R
import com.example.riddler.data.repo.GameRepository
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
        println(quizId)
        hostVM = ViewModelProvider(this).get(HostViewModel::class.java)
        hostVM.setCurrentQuestions(quizVM.getQuestions(quizId))

        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.hostContainer, HostLobbyFragment())
        ft.commit()


    }

}