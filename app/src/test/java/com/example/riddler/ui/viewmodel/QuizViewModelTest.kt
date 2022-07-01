package com.example.riddler.ui.viewmodel

import android.os.UserManager
import com.example.riddler.TriviaQuestions
import com.example.riddler.TriviaRepo
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
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*

import org.junit.Test
import retrofit2.http.GET

class QuizViewModelTest {


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



//quiz
    val quiz1 = Quiz("Joe","Best Quize","people Around the World","General",23)

//trivia Questions
    val listofTrivia : List<TriviaQuestions.Question> = listOf()

    val lobbyPlayers = mockk<LobbyPlayers>()



    val UserProfile = mockk<UserProfile>()


    val repo = mockk<TriviaRepo>()
    val quizRepo = mockk<QuizRepository>()
    val underTest = QuizViewModel(
        repo = repo,
        quizRepo = quizRepo,
    )
    val myQuiz:Quiz = Quiz()


    @Test
    fun testForCreateQuizFromApi() {
        val repo = mockk<TriviaRepo>()
        val appModule = mockk<AppModule>()
        val quizRepo = mockk<QuizRepository>()
        val underTest = QuizViewModel(
            repo = repo,
            quizRepo = quizRepo,
        )

    }

    @Test
    fun testForInsertQuestions() {
        coEvery { underTest.insertQuestions(listofTrivia,quiz1) } just Runs

    }

    @Test
    fun testForGetQuestions() {
        every { underTest.getQuestions(23) } just Runs

        //verify { quizRepo.getQuizQuestion(23) }
    }

    @After
    fun tearDown(){
        unmockkAll()
    }
}

infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
