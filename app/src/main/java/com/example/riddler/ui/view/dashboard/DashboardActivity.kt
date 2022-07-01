package com.example.riddler.ui.view.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.NewTriviaActivity
import com.example.riddler.OnboardActivity
import com.example.riddler.R
import com.example.riddler.TriviaQuizActivity
import com.example.riddler.data.model.Quiz
import com.example.riddler.ui.adapters.DashboardQuizListAdapter
import com.example.riddler.ui.view.MainActivity
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.view.player.PlayerActivity
import com.example.riddler.ui.view.settings.SettingsActivity
import com.example.riddler.ui.viewmodel.DashboardViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {
    lateinit var data : ArrayList<Quiz>
    lateinit var adapter: DashboardQuizListAdapter
    val auth = Firebase.auth

    lateinit var vm : DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)




        vm = DashboardViewModel()

        if(auth.currentUser == null) {
            Toast.makeText(this, "No users are signed in, redirecting to Log On screen...", Toast.LENGTH_LONG).show()
            signOut()
        }

        data = vm.quizList

        val recyclerView = findViewById<RecyclerView>(R.id.dash_recyclerview)
        adapter = DashboardQuizListAdapter(data, onQuizItemClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.dash_hostGameButton).setOnClickListener {
            val intent = Intent(this, HostActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.dash_joinGameButton).setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)

            var m_Text = ""

            val input = EditText(this)
            input.setHint("Enter Text")

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                .setTitle("Enter Game PIN")
                .setView(input)
                .setPositiveButton("OK") { dialog, which ->
                    m_Text = input.text.toString()
                    if (!m_Text.isEmpty()) {
                        intent.putExtra("pin", m_Text)
                        startActivity(intent)
                    }
                    else
                        Toast.makeText(this, "Please enter the game PIN", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }

            builder.show()

        }

        findViewById<Button>(R.id.dash_addButton).setOnClickListener {
            val intent = Intent(this, NewTriviaActivity::class.java)
            startActivity(intent)
        }

        vm.userProfile.observe(this) {
            println("it.email")
            if(it != null)
                findViewById<TextView>(R.id.dash_welcome).setText("Welcome, ${it.firstName} ${it.lastName}!")
        }

        vm.fetchUserProfileInfo()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*if(item.itemId == R.id.toolbar_settings){
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }*/
        when (item.itemId){
            R.id.toolbar_settings -> {
                intent = Intent(this, SettingsActivity::class.java)
                startForResult.launch(intent)
            }
            R.id.toolbar_logout -> {
                signOut()
            }
        }

        return true
    }

    val onQuizItemClick = fun(index : Int) {
        val intent = Intent(this, TriviaQuizActivity::class.java)
        //intent.putExtra("quiz", data.get(index))
        startActivity(intent)
    }

    fun signOut(){
        auth.signOut()
        intent = Intent(this, OnboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            vm.fetchUserProfileInfo()
        }
    }
}