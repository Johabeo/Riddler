package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.model.UserProfile
import com.example.riddler.data.repo.FirestoreRepository

class DashboardViewModel : ViewModel() {
    //todo: dependency injection instead of instantiating repo
    val repo : FirestoreRepository
    var quizList : ArrayList<Quiz>
    val userProfile : MutableLiveData<UserProfile>

    init{
        //todo: livedata
        quizList = arrayListOf(
            Quiz( "1", "A Math Quiz", "A simple quiz on derivatives", "Math"),
            Quiz( "1", "A History Quiz", "Battle of Schrute Farms", "History"),
            Quiz( "1", "A Programming Quiz", "Pointers, system calls, interrupts", "Computer Science"),
            Quiz( "1", "A Politics Quiz", "Revolution 101", "Politics"),
            Quiz( "1", "A Bonus Quiz", "Linux fundamentals", "Computer Science")
        )
        repo = FirestoreRepository()
        userProfile = repo.userProfile
    }

    fun fetchUserProfileInfo(){
        repo.fetchUserProfileInfo()
    }

}