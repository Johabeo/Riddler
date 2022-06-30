package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.data.model.UserProfile
import com.example.riddler.data.repo.FirestoreRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val repo: FirestoreRepository) : ViewModel(){

    val userProfile : MutableLiveData<UserProfile>

    init{
        userProfile = repo.userProfile
    }

    fun fetchUserProfileInfo(){
        repo.fetchUserProfileInfo()
    }

    fun updateUserProfile(profile: UserProfile){
        repo.updateUserProfileInfo(profile)
    }

}