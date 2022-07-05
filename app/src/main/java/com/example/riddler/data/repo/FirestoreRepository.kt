package com.example.riddler.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.model.UserProfile
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class FirestoreRepository() {
    val db = FirebaseFirestore.getInstance()

    //auth.currentUser is current user, null if no user is logged in
    val auth = Firebase.auth

    //observe userProfile throughout the app, the changes will be reflected if user updates their info
    val userProfile : MutableLiveData<UserProfile> by lazy {
        MutableLiveData<UserProfile>()
    }

    //TODO use rxkotlin and validation
    fun insertQuiz(quiz: Quiz) {

        db.collection("quizzes")
            .add(quiz)
            .addOnSuccessListener {
                println("succeeded in adding ${quiz} to firestore") //change it later
            }
            .addOnFailureListener {
                println("failed in adding ${quiz} to firestore") //change it later
            }
    }

    fun insertUserProfileInfo(userProfile: UserProfile){
        db.collection("users")
            .add(userProfile)
            .addOnSuccessListener {
                println("succeeded in adding ${userProfile} to firestore") //change it later
            }
            .addOnFailureListener {
                println("failed in adding ${userProfile} to firestore") //change it later
            }

    }

    //firebase is async, so it's best to use livedata
    fun fetchUserProfileInfo(){
        if(auth.currentUser != null){
            var profile : UserProfile? = null
            db.collection("users")
                .whereEqualTo("email", auth.currentUser!!.email)
                .get()
                .addOnSuccessListener {
                    profile = it.documents.firstOrNull()?.toObject(UserProfile::class.java)
                    userProfile.postValue(profile)
                }
                .addOnFailureListener {
                    Timber.tag("Auth error")
                        .e("Invalid user profile")
                }
        }
    }

    fun updateUserProfileInfo(userProfile: UserProfile){
        db.collection("users")
            .whereEqualTo("email", userProfile.email)
            .get()
            .addOnCompleteListener {
                val doc = it.result.documents.firstOrNull()
                if(doc != null){
                    val update: MutableMap<String, Any> = HashMap()
                    //there will be a separate function for that, since email is the unique user id
                    //update["email"] = userProfile.email
                    update["firstName"] = userProfile.firstName
                    update["lastName"] = userProfile.lastName
                    update["profilePic"] = userProfile.profilePic
                    db.collection("users").document(doc.id).set(update, SetOptions.merge())
                    //update live data if any changes
                    fetchUserProfileInfo()
                }
            }
    }

    //email is unique to every user and it's used for user id
    fun getUserEmailId() : String?{
        if(!isUserLoggedIn()){
            return auth.currentUser!!.email
        }
        return null
    }

    //use to check if user is logged in
    fun isUserLoggedIn() : Boolean {
        return auth.currentUser != null
    }

    //check user credentials
    //if successful, updateUserEmail or updateUserPassword will immediately be called
    //if unsuccessful, an error will be displayed in the change email/password dialog
    fun verifyCredentials(email: String, password: String) : Boolean{
        var result = false
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser!!.reauthenticate(credential)
            .addOnSuccessListener {
                result = true
            }
            .addOnFailureListener {
                result = false
            }

        return result
    }

    //reauthenticate, update email, update user profile, refresh user profile
    fun updateUserEmail(oldEmail: String, newEmail: String, password: String){
        var result = false
        val credential = EmailAuthProvider.getCredential(oldEmail, password)
        auth.currentUser!!.reauthenticate(credential)
            .addOnSuccessListener {
                auth.currentUser!!.updateEmail(newEmail)
                    .addOnSuccessListener {
                        db.collection("users")
                            .whereEqualTo("email", oldEmail)
                            .get()
                            .addOnCompleteListener {
                                val doc = it.result.documents.firstOrNull()
                                if (doc != null) {
                                    val update: MutableMap<String, Any> = HashMap()
                                    update["email"] = newEmail
                                    db.collection("users").document(doc.id).set(update, SetOptions.merge())
                                    fetchUserProfileInfo()
                                }
                            }
                        result = true
                        //fetchUserProfileInfo()
                    }.addOnFailureListener {
                        result = false
                    }
            }
            .addOnFailureListener {
                result = false
            }
    }

    fun updateUserPassword(email: String, oldPassword: String, newPassword: String) : Boolean {
        var result = false
        val credential = EmailAuthProvider.getCredential(email, oldPassword)
        auth.currentUser!!.reauthenticate(credential)
            .addOnSuccessListener {
                auth.currentUser!!.updatePassword(newPassword)
                    .addOnSuccessListener {
                        result = true
                        fetchUserProfileInfo()
                    }
                    .addOnFailureListener {
                        result = false
                    }
            }
        return result
    }


    //TODO use rxkotlin and validation
    fun getAllQuizByUser(user: String) : List<Quiz> {
        var quizList : ArrayList<Quiz> = ArrayList<Quiz>()

        db.collection("quizzes")
            .whereEqualTo("owner", user)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val quiz = document.toObject(Quiz::class.java)
                    quizList.add(quiz)
                }
            }
            .addOnFailureListener {
                println("failed in getting quizzes from ${user}") //change it later
            }

        return quizList
    }

    //TODO use rxkotlin and validation
    fun getAllQuiz() : List<Quiz> {
        var quizList : ArrayList<Quiz> = ArrayList<Quiz>()

        db.collection("quizzes")
            .whereEqualTo("id",1)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    println(document.data)
                }
            }
            .addOnFailureListener {
                println("failed in getting quizzes from ") //change it later
            }

        return quizList
    }
    fun getQuiz() {
        val docRef = db.collection("quizzes").document("101349")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                println(snapshot.data)
            } else {
            }
        }

    }
}