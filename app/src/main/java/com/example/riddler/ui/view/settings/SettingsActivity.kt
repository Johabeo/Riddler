package com.example.riddler.ui.view.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.data.model.Avatars
import com.example.riddler.data.model.UserProfile
import com.example.riddler.ui.adapters.AvatarPickerAdapter
import com.example.riddler.ui.viewmodel.SettingsViewModel
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    }

    fun updatePassword(view: View){

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