package com.example.riddler.ui.viewmodel

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.model.UserProfile
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.QuizRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@RelaxedMockK
class DiscoverViewModelTest {
    val quizRepo = mockk<QuizRepository>()
    val underTest = DiscoverViewModel(
        quizRepo = quizRepo,
    firebaseRepository = FirestoreRepository()
    )


    @Before
    fun setUp() {
        mockkStatic(FirestoreRepository::class)
        every { FirebaseFirestore.getInstance() }returns mockk(relaxed = true)
        val db = FirebaseFirestore.getInstance()
        val auth = Firebase.auth
        val userProfile : MutableLiveData<UserProfile>
        val quizRepo = mockk<QuizRepository>()
        val underTest = DiscoverViewModel(
            quizRepo = quizRepo,
        firebaseRepository = FirestoreRepository())

    }


}

private infix fun Unit.just(runs: Runs) {

}

