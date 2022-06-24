package com.example.riddler.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.riddler.R


class SignUpFragment(val signUp : (String, String) -> Unit) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val emailText = view.findViewById<EditText>(R.id.onboarding_signup_emailEditText)
        val passwordText = view.findViewById<EditText>(R.id.onboarding_signup_passwordEditText)

        view.findViewById<Button>(R.id.onboarding_signup_signupButon).setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            if(validate(email, password))
                signUp(email,password)
            else
                Toast.makeText(context, "Invalid email or short password", Toast.LENGTH_LONG).show()
        }

        view.findViewById<Button>(R.id.onboard_signin_backButton).setOnClickListener {
            if(parentFragmentManager.backStackEntryCount > 0)
                parentFragmentManager.popBackStack()
        }


        return view
    }

    fun validate(email : String, password : String) : Boolean{

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return false
        }
        //firebase will perform a check on password strength, but for now we'll check the length
        if(password.length < 5){
            return false
        }

        return true
    }
}