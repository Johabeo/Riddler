package com.example.riddler.ui

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.riddler.R
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment(val signUp : (String, String, String, String) -> Unit) : Fragment() {


    lateinit var inputEmail: TextInputLayout
    lateinit var inputPassword: TextInputLayout
    lateinit var inputFirstName: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val emailText = view.findViewById<EditText>(R.id.onboarding_signup_emailEditText)
        val passwordText = view.findViewById<EditText>(R.id.onboarding_signup_passwordEditText)
        passwordText.transformationMethod = PasswordTransformationMethod.getInstance()
        val firstNameText = view.findViewById<EditText>(R.id.onboarding_signup_firstNameEditText)
        val lastNameText = view.findViewById<EditText>(R.id.onboarding_signup_lastNameEditText)

        inputEmail = view.findViewById<TextInputLayout>(R.id.onboarding_signup_emailInputLayout)
        inputPassword = view.findViewById<TextInputLayout>(R.id.onboarding_signup_passwordInputLayout)
        inputFirstName = view.findViewById<TextInputLayout>(R.id.onboarding_signup_firstNameInputLayout)

        view.findViewById<Button>(R.id.onboarding_signup_signupButon).setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            val firstName = firstNameText.text.toString()
            val lastName = lastNameText.text.toString()
            if(validate(email, password, firstName)) {
                signUp(email, password, firstName, lastName)
            }

        }

        view.findViewById<ImageView>(R.id.onboard_signin_backButton).setOnClickListener {
            if(parentFragmentManager.backStackEntryCount > 0)
                parentFragmentManager.popBackStack()
        }

        passwordText.setOnFocusChangeListener { _, focused ->
            val password = passwordText.text.toString()
            if(!focused) {
                if (password.isEmpty()) {
                    inputPassword.helperText = "*Password Required"
                } else {
                    inputPassword.helperText = ""
                }
            }
        }

        emailText.setOnFocusChangeListener { _, focused ->
            val email = emailText.text.toString()
            if(!focused) {
                if (email.isEmpty()) {
                    inputEmail.helperText = "*Email Required"
                } else {
                    inputEmail.helperText = ""
                }
            }
        }

        firstNameText.setOnFocusChangeListener { _, focused ->
            val password = firstNameText.text.toString()
            if(!focused) {
                if (password.isEmpty()) {
                    inputFirstName.helperText = "*First Name Required"
                } else {
                    inputFirstName.helperText = ""
                }
            }
        }
        return view
    }

    fun validate(email : String, password : String, firstName : String) : Boolean{

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context, "*Invalid Email", Toast.LENGTH_LONG).show()
            return false
        }
        //firebase will perform a check on password strength, but for now we'll check the length
        else if (password.length < 8) {
            inputPassword.helperText = "Minimum 8 Character Password"
            return false
        }
        else if (!password.matches(".*[A-Z].*".toRegex())) {
            inputPassword.helperText = "*Must Contain 1 Upper-case Character"
            return false
        }
        else if (!password.matches(".*[a-z].*".toRegex())) {
            inputPassword.helperText = "*Must Contain 1 Lower-case Character"
            return false
        }
        else if (!password.matches(".*[@#\$%^$+=].*".toRegex())) {
            inputPassword.helperText = "*Must Contain 1 Special Character (@#\$%^\$+=)"
            return false
        }
        return true
    }
}