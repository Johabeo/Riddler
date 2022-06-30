package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.data.model.UserProfile
import com.example.riddler.data.repo.FirestoreRepository

class SettingsViewModel : ViewModel(){
    val repo : FirestoreRepository
    val userProfile : MutableLiveData<UserProfile>
    //todo: dependency injection instead of instantiating repo

    init{
        repo = FirestoreRepository()
        userProfile = repo.userProfile
    }

    fun fetchUserProfileInfo(){
        repo.fetchUserProfileInfo()
    }

    fun updateUserProfile(profile: UserProfile){
        repo.updateUserProfileInfo(profile)
    }

}