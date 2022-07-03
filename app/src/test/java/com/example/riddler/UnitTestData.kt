package com.example.riddler

import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.di.AppModule
import com.example.riddler.data.model.*
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.GameRepository
import com.example.riddler.data.repo.QuizRepository
import com.example.riddler.ui.adapters.DashboardQuizListAdapter
import com.example.riddler.ui.adapters.LeaderboardAdapter
import com.example.riddler.ui.adapters.PlayerAdapter
import com.example.riddler.ui.view.dashboard.DashboardActivity
import com.example.riddler.ui.view.host.HostActivity
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestData {

    //Dao
    val quizDao = mockk<QuizDao>()

    //Database
    val appDatabase = mockk<AppDatabase>()

    //Module
    val appModule = mockk<AppModule>()

    //model
    val lobby = mockk<Lobby>()
    val questions = mockk<Questions>()
    val quiz = mockk<Quiz>()
    val quizGame = mockk<QuizGame>()
    val userModel = mockk<UserProfile>()


    //repo
    val firestoreRepository = mockk<FirestoreRepository>()
    val gameRepository = mockk<GameRepository>()
    val quizRepository = mockk<QuizRepository>()

    //Adapter
    val dashboardQuizListAdapter = mockk<DashboardQuizListAdapter>()
    val leaderboardAdapter = mockk<LeaderboardAdapter>()
    val playerAdapter = mockk<PlayerAdapter>()
    //view
    val dashboardActivity = mockk<DashboardActivity>()
    val hostActivity = mockk<HostActivity>()
    val player1 = Player("a",0,false)
    val player2 = Player("b",0,false)


//quiz
    val quiz1 = Quiz("Joe","Best Quize","people Around the World","General",23)
//Questions
    val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")
//Question
    val question: TriviaQuestions.Question = TriviaQuestions.Question("Science:Computers"
    ,"Multiple Choice","Easy","what is JVM"
    ,"Java Virtual Machine", listOf("Joint Mechanical Machine", "Java Volatile Memory", "JSON Volume Memory"))
//TriviaQuestions
    val triviaQuestions: TriviaQuestions = TriviaQuestions(1,listOf(question))

    val lobbyPlayers = mockk<LobbyPlayers>()
    val UserProfile = mockk<UserProfile>()
    val lobbyPlayersList = listOf(lobbyPlayers)
    val userProfileList = listOf(UserProfile)
    val lobbyPlayersList2 = listOf(lobbyPlayers)
    val userProfileList2 = listOf(UserProfile)



//Observable
var fakeList :List<Quiz> = (listOf<Quiz>(
    Quiz("G1","fromtest","","",1)
))
    var observableFakelist = Observable.fromArray((fakeList))

    var fakelist2 : List<Questions> = (listOf<Questions>(Questions(1,"What's your group", "G1","G2","G3","G4","G1")
    ))
    var observableFakelist2 = Observable.fromArray((fakelist2))

}