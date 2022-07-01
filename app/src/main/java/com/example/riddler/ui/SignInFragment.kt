package com.example.riddler.ui

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.riddler.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment(val signIn : (String, String) -> Unit, val setSignUpFragment : () -> Unit) : Fragment() {

    lateinit var inputPassword : TextInputLayout
    lateinit var inputEmail : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val emailText = view.findViewById<EditText>(R.id.onboarding_signin_email)
        val passwordText = view.findViewById<EditText>(R.id.onboarding_signin_password)
        passwordText.transformationMethod = PasswordTransformationMethod.getInstance()

        inputPassword = view.findViewById(R.id.password_layout)
        inputEmail = view.findViewById(R.id.onboarding_signup_emailInputLayout)


        view.findViewById<Button>(R.id.onboarding_signin_signinButon).setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            if(validate(email, password)){
                signIn(email, password)
            }
            else
                Toast.makeText(context, "Invalid Email/Password Combination", Toast.LENGTH_LONG).show()
        }

        view.findViewById<TextView>(R.id.onboard_signin_signupButton).setOnClickListener {
            setSignUpFragment()
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
        return view
    }

    fun validate(email : String, password : String) : Boolean{
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.helperText = "*Please Enter a Valid Email"
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
        else if (password.length < 8) {
            inputPassword.helperText = "Minimum 8 Character Password"
            return false
        }
        return true
    }
}