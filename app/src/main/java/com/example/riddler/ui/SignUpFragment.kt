package com.example.riddler.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
            signUp(email,password)
        }

        view.findViewById<Button>(R.id.onboard_signin_backButton).setOnClickListener {
            if(parentFragmentManager.backStackEntryCount > 0)
                parentFragmentManager.popBackStack()
        }


        return view
    }
}