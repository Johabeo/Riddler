package com.example.riddler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.google.android.gms.common.api.Api
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RelaxedMockK
@RunWith(AndroidJUnit4::class)
class ApiViewModelTest {
    private lateinit var viewModel: ApiViewModel
    private lateinit var api: RetroApiInterface
    private lateinit var quiz: Quiz
    private lateinit var questions: Questions
    private lateinit var liveData: LiveData<Quiz>
    private lateinit var mutableLiveData: MutableLiveData<Quiz>
    private lateinit var job: Job
    private lateinit var scope: CoroutineScope
    lateinit var triviaRepo: TriviaRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        triviaRepo = mockk()
        api = mockk()
        quiz = mockk()
        questions = mockk()
        liveData = mockk()
        mutableLiveData = mockk()
        job = mockk()
        scope = mockk()
        viewModel = ApiViewModel(triviaRepo)
    }

    val repo = mockk<TriviaRepo>()
    val underTest = ApiViewModel(
        repo = repo,
    )


    @Test
    fun `function to get al the trivia questions from api`() {
        val triviaQuestions = MutableLiveData<TriviaQuestions>()
        val job : Job? = null
        val scope = CoroutineScope(Dispatchers.Main )


        coEvery { underTest.getAllTriviaQuestions(10,1,"Easy","Computer:general") } returns Unit
        verify {
            runBlocking {
                repo.getAllTriviaQuestions(10,1,"Easy","Computer:general")
            }

        }


    }

}

infix fun <T, B> MockKStubScope<T, B>.returns(triviaQuestionList: List<TriviaQuestions>) {

}
