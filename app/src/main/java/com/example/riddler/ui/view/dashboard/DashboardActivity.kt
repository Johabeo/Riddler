package com.example.riddler.ui.view.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.MainActivity
import com.example.riddler.NewTriviaActivity
import com.example.riddler.R
import com.example.riddler.TriviaQuizActivity
import com.example.riddler.data.model.Quiz
import com.example.riddler.ui.adapters.DashboardQuizListAdapter
import com.example.riddler.ui.view.host.HostActivity
import com.example.riddler.ui.view.player.PlayerActivity
import com.example.riddler.ui.view.settings.SettingsActivity
import com.example.riddler.ui.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {
    lateinit var data : ArrayList<Quiz>
    lateinit var adapter: DashboardQuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        val vm = DashboardViewModel()

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
                    if (!m_Text.isEmpty())
                        startActivity(intent)
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


        val intent = Intent(this, MainActivity::class.java)
        //uncomment if you want to open NewTriviaActivity immediately
        startActivity(intent)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.toolbar_settings){
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    val onQuizItemClick = fun(index : Int) {
        val intent = Intent(this, TriviaQuizActivity::class.java)
        intent.putExtra("quiz", data.get(index))
        startActivity(intent)
    }
}