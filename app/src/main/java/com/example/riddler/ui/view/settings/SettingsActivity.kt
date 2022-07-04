package com.example.riddler.ui.view.settings

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.Util
import com.example.riddler.data.model.Avatars
import com.example.riddler.data.model.UserProfile
import com.example.riddler.ui.adapters.AvatarPickerAdapter
import com.example.riddler.ui.viewmodel.SettingsViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    @Inject
    lateinit var vm : SettingsViewModel

    //class-level because I might need them elsewhere idk
    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var emailLabel: TextView
    lateinit var avatarPicture : ImageView
    var profilePic = 0
    lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = getSharedPreferences("prefs", MODE_PRIVATE)

        firstNameEditText = findViewById(R.id.set_firstNameEditText)
        lastNameEditText = findViewById(R.id.set_lastNameEditText)
        emailLabel = findViewById(R.id.set_emailTextView)
        avatarPicture = findViewById(R.id.set_avatarPic)

        vm.userProfile.observe(this){
            firstNameEditText.setText(it.firstName)
            lastNameEditText.setText(it.lastName)
            emailLabel.setText(it.email)
            profilePic = it.profilePic
            avatarPicture.setImageResource(Avatars.avatarsList.get(profilePic))
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

    fun updateUserProfile(view: View){
        if(validate()){
            val profile = UserProfile()
            profile.email = emailLabel.text.toString()
            profile.firstName = firstNameEditText.text.toString()
            profile.lastName = lastNameEditText.text.toString()
            profile.profilePic = profilePic
            vm.updateUserProfile(profile)
            Toast.makeText(this, "Updating user settings...", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateEmail(view: View){
        var alertDialog: AlertDialog? = null

        val dialogView = layoutInflater.inflate(R.layout.dialog_email_change, null)
        val newEmailLayout = dialogView.findViewById<TextInputLayout>(R.id.changeEmail_newEmailTextLayout)
        val passwordLayout = dialogView.findViewById<TextInputLayout>(R.id.changeEmail_currentPasswordTextLayout)
        val newEmailEditText = dialogView.findViewById<EditText>(R.id.changeEmail_emailEditText)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.changeEmail_passwordEditText)
        val updateButton = dialogView.findViewById<Button>(R.id.changeEmail_changeEmailButton)
        val cancelButton = dialogView.findViewById<Button>(R.id.changeEmail_CancelButton)
        val oldEmail = emailLabel.text.toString()

        val validateEmail = fun (newEmail: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
        }
        val checkPassword = fun(password: String) : Boolean{
            val savedPassword = preferences.getString("pwdHash", "")
            val providedPassword = Util.computeSha256(password)
            return savedPassword == providedPassword
        }
        val commitUpdateEmail = fun(newEmail : String, password: String){
            vm.updateUserEmail(oldEmail, newEmail, password)
            Toast.makeText(this, "User email updated", Toast.LENGTH_LONG).show()
            alertDialog?.dismiss()
        }

        updateButton.setOnClickListener {
            val emailStr = newEmailEditText.text.toString()
            val passwordStr = passwordEditText.text.toString()
            if(validateEmail(emailStr) && checkPassword(passwordStr)){
                commitUpdateEmail(emailStr, passwordStr)
            } else {
                if(!validateEmail(emailStr)){
                    newEmailLayout.helperText = "Please enter a valid email"
                } else {
                    newEmailLayout.helperText = ""
                }
                if(!checkPassword(passwordStr)){
                    passwordLayout.helperText = "Password doesn't match"
                } else {
                    passwordLayout.helperText = ""
                }
            }
        }
        cancelButton.setOnClickListener {
            alertDialog?.dismiss()
        }

        val builder = AlertDialog.Builder(this).apply {
            setView(dialogView)
            setCancelable(true)
        }

        alertDialog = builder.create()
        alertDialog.show()

    }

    fun updatePassword(view: View){
        var alertDialog: AlertDialog? = null

        val dialogView = layoutInflater.inflate(R.layout.dialog_password_change, null)
        val currentPasswordLayout = dialogView.findViewById<TextInputLayout>(R.id.changePassword_currentPasswordTextLayout)
        val newPasswordLayout = dialogView.findViewById<TextInputLayout>(R.id.changePassword_newPasswordTextLayout)
        val confirmPasswordLayout = dialogView.findViewById<TextInputLayout>(R.id.changePassword_confirmPasswordTextLayout)
        val currentPasswordEditText = dialogView.findViewById<EditText>(R.id.changePassword_currentPasswordEditText)
        val newPasswordEditText = dialogView.findViewById<EditText>(R.id.changePassword_newPasswordEditText)
        val confirmPasswordEditText = dialogView.findViewById<EditText>(R.id.changePassword_confirmPasswordEditText)
        val updateButton = dialogView.findViewById<Button>(R.id.changePassword_changePasswordButton)
        val cancelButton = dialogView.findViewById<Button>(R.id.changePassword_CancelButton)
        val email = emailLabel.text.toString()

        val checkPassword = fun(password: String) : Boolean{
            val savedPassword = preferences.getString("pwdHash", "")
            val providedPassword = Util.computeSha256(password)
            return savedPassword == providedPassword
        }

        val validatePassword = fun(newPassword: String, confirmPassword: String) : Boolean{
            if (newPassword.length < 8) {
                newPasswordLayout.helperText = "Minimum 8 Character Password"
                return false
            }
            else if (!newPassword.matches(".*[A-Z].*".toRegex())) {
                newPasswordLayout.helperText = "*Must Contain 1 Upper-case Character"
                return false
            }
            else if (!newPassword.matches(".*[a-z].*".toRegex())) {
                newPasswordLayout.helperText = "*Must Contain 1 Lower-case Character"
                return false
            }
            else if (!newPassword.matches(".*[@#\$%^$+=0-9].*".toRegex())) {
                newPasswordLayout.helperText = "*Must Contain 1 Digit and/or Special Character (@#\$%^\$+=)"
                return false
            }
            else if(newPassword != confirmPassword) {
                confirmPasswordLayout.helperText = "Passwords do not match"
                return false
            }
            return true
        }

        val commitUpdatePassword = fun(email : String, oldPassword: String, password: String){
            vm.updateUserPassword(email, oldPassword, password)
            Toast.makeText(this, "User password updated", Toast.LENGTH_LONG).show()
            alertDialog?.dismiss()
        }

        updateButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString()
            val oldPassword = currentPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            if(checkPassword(oldPassword)){
                if(validatePassword(newPassword, confirmPassword)){
                    commitUpdatePassword(email, oldPassword, newPassword)
                }
            } else {
                currentPasswordLayout.helperText = "Wrong password!"
            }
        }
        cancelButton.setOnClickListener {
            alertDialog?.dismiss()
        }

        val builder = AlertDialog.Builder(this).apply {
            setView(dialogView)
            setCancelable(true)
        }

        alertDialog = builder.create()
        alertDialog.show()
    }

    fun pickAvatarPicture(view: View){
        var alertDialog: AlertDialog? = null

        val setItem = fun(resource: Int){
            profilePic = resource
            updateUserProfile(view)
            Toast.makeText(this, "Profile picture updated", Toast.LENGTH_SHORT).show()
            alertDialog?.dismiss()
        }
        val dialogView = layoutInflater.inflate(R.layout.dialog_pick_avatar, null)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.pickAv_recyclerView)
        val adapter = AvatarPickerAdapter(Avatars.avatarsList, setItem)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)

        val builder = AlertDialog.Builder(this).apply {
            setView(dialogView)
            setCancelable(true)
        }

        alertDialog = builder.create()
        alertDialog.show()

    }
}