package com.example.riddler.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddler.R
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

    fun verifyCredentials(email: String, password: String) : Boolean{
        return repo.verifyCredentials(email, password)
    }

    fun updateUserEmail(oldEmail: String, newEmail: String, password: String){
        repo.updateUserEmail(oldEmail, newEmail, password)
    }

    fun updateUserPassword(email: String, oldPassword: String, password: String){
        repo.updateUserPassword(email, oldPassword, password)
    }

}