package com.example.riddler.ui.viewmodel

import android.os.UserManager
import com.example.riddler.RetroApiInterface
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
import io.reactivex.rxjava3.core.Observable
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
    val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")

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
    val fakeList : List<Quiz> = listOf<Quiz>(Quiz("G1", "fromTest","quiq for testing","general","Easy"))

    val fakeQuestions : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))

    var observableFakeList = Observable.fromArray(fakeList)
    val questions = Questions(
        1,"whats your group","G1"
        ,"G2","G3","Dont know"
        ,"G1",1)

    //List of questions
    val listOfQuestions1 : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))
    val quiz3 = Quiz("Jay","From test","Atest Question from test","General","Easy")
    val quiz2 = Quiz("G1","Our Team","test1",
        "test to insert quiz","General",1)
    val favoriteQuiz1 = FavoriteQuiz(
        1,1,1)
    val favoriteQuiz2 = FavoriteQuiz(
        2,1,1)


    @Test
    fun `test to check if InsertQuestions() is working`() {
        runBlocking {
            val quizRepo = mockk<QuizRepository>()
            val retroApiInterface = mockk<RetroApiInterface>()
            val underTest = QuizViewModel(
                repo = TriviaRepo(retroApiInterface),
                quizRepo = quizRepo,
            )
            every { quizRepo.insertQuestions(any()) } returns Unit
            quizRepo.insertQuestions(questions)
            verify { quizRepo.insertQuestions(questions) }

        }
    }

    @Test
    fun `test to check if GetQuestions() is working`() {
        every { quizRepo.getQuizQuestion(1) } returns listOfQuestions1
        quizRepo.getQuizQuestion(1)
        verify { quizRepo.getQuizQuestion(1) }


    }

    @Test
    fun `test to check if GetQuiz() is working`() {
        every { quizRepo.getMyQuizzes(1) } returns Observable.just(fakeList)
        quizRepo.getMyQuizzes(1)
        verify { quizRepo.getMyQuizzes(1) }

    }

    @After
    fun tearDown(){
        unmockkAll()
    }
}

infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
