package com.example.riddler

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.riddler.ui.SignInFragment
import com.example.riddler.ui.SignUpFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class OnboardActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            auth.signOut()
        }

        val fragment = SignInFragment(signIn, setSignupFragment)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.onboard_fragmentContainer, fragment)
            .commit()


    }

    fun openMainActivity(){
        Log.d("firebase auth", "inside openMainActivity")
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
                    openMainActivity()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebase auth: ", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    val signUp = fun(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("firebase auth: ", "createUserWithEmail:success")
                    val user = auth.currentUser
                    openMainActivity()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebase auth: ", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
}