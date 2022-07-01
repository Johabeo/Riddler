package com.example.riddler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.riddler.data.model.UserProfile
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.ui.SignInFragment
import com.example.riddler.ui.SignUpFragment
import com.example.riddler.ui.view.MainActivity
import com.example.riddler.ui.view.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class OnboardActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var repo : FirestoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            //auth.signOut()
            println(currentUser.providerData.get(0).email)
            openMainActivity()

        }

        val fragment = SignInFragment(signIn, setSignupFragment)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.onboard_fragmentContainer, fragment)
            .commit()


    }

    fun openMainActivity(){
        Log.d("firebase auth", "inside openMainActivity")
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    val setSignupFragment = fun(){
        val signUpFragment = SignUpFragment(signUp)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.onboard_fragmentContainer, signUpFragment)
            .addToBackStack("fragment")
            .commit()
    }

    val signIn = fun(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("firebase auth: ", "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this, "Sign In Successful", Toast.LENGTH_LONG).show()
                    openMainActivity()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebase auth: ", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                    //updateUI(null)
                }
            }
    }

    val signUp = fun(email: String, password: String, firstName: String, lastName: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("firebase auth: ", "createUserWithEmail:success")
                    val user = auth.currentUser
                    if(user != null){
                        val userProfile = UserProfile()
                        userProfile.firstName = firstName
                        userProfile.lastName = lastName
                        userProfile.email = email
                        repo.insertUserProfileInfo(userProfile)
                        Toast.makeText(this, "Account Created, Welcome Aboard", Toast.LENGTH_LONG).show()
                        openMainActivity()
                    } else {
                        //throw RuntimeException("OnboardActivity.signUp: signed up, but user is invalid")
                        Toast.makeText(baseContext, "OnboardActivity.signUp: signed up, but user is invalid",
                            Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebase auth: ", "createUserWithEmail:fail", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}