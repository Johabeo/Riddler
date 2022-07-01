package com.example.riddler.ui.view.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.riddler.R
import com.example.riddler.data.model.UserProfile
import com.example.riddler.ui.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {
    lateinit var vm : SettingsViewModel

    //class-level because I might need them elsewhere idk
    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var emailLabel: TextView
    lateinit var updateEmailButton: Button
    lateinit var updatePasswordButton: Button
    lateinit var saveProfileButton: Button
    var initialSetup = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vm = SettingsViewModel()

        firstNameEditText = findViewById(R.id.set_firstNameEditText)
        lastNameEditText = findViewById(R.id.set_lastNameEditText)
        emailLabel = findViewById(R.id.set_emailTextView)
        updateEmailButton = findViewById(R.id.set_updateEmailButton)
        updatePasswordButton = findViewById(R.id.set_updatePasswordButton)
        saveProfileButton = findViewById(R.id.set_saveProfileSettingsButton)

        saveProfileButton.setOnClickListener {
            updateUserProfile()
        }

        vm.userProfile.observe(this){
            firstNameEditText.setText(it.firstName)
            lastNameEditText.setText(it.lastName)
            emailLabel.setText(it.email)
            if(initialSetup)
                initialSetup = false
            else
                Toast.makeText(this, "User settings updated", Toast.LENGTH_SHORT).show()
        }

        vm.fetchUserProfileInfo()
    }

    //the back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
    }

    fun validate() : Boolean {
        if(firstNameEditText.text.toString().isEmpty()){
            Toast.makeText(this, "Please enter first name or nickname.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun updateUserProfile(){
        if(validate()){
            val profile = UserProfile()
            profile.email = emailLabel.text.toString()
            profile.firstName = firstNameEditText.text.toString()
            profile.lastName = lastNameEditText.text.toString()
            vm.updateUserProfile(profile)
        }
    }
}