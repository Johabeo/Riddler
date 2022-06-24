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


class SignInFragment(val signIn : (String, String) -> Unit, val setSignUpFragment : () -> Unit) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val emailText = view.findViewById<EditText>(R.id.onboarding_signin_email)
        val passwordText = view.findViewById<EditText>(R.id.onboarding_signin_password)

        view.findViewById<Button>(R.id.onboarding_signin_signinButon).setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            signIn(email, password)
        }

        view.findViewById<Button>(R.id.onboard_signin_signupButton).setOnClickListener {
            setSignUpFragment()
        }

        return view
    }

}