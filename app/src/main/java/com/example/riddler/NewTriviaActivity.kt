package com.example.riddler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class NewTriviaActivity : AppCompatActivity() {

    //lateinit var vm: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_trivia)

        /*val inter = RetroApiInterface.create()
        val repo = TriviaRepo(inter)
        vm = ApiViewModel(repo)*/

        val editNumOfQs = findViewById<EditText>(R.id.editNumOfQs)

        val categoryDropdown = findViewById<Spinner>(R.id.categoryDropdown)
        val categories = arrayOf("General Knowledge", "Books", "Film", "Music", "Musicals & Theatres",
        "Television", "Video Games", "Board Games", "Science & Nature", "Computers", "Mathematics",
        "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebrities", "Animals",
        "Vehicles", "Comics", "Gadgets", "Anime & Manga", "Cartoons & Animation")
        val categoryAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, categories)
        categoryDropdown.adapter = categoryAdapter

        val difficultyDropdown = findViewById<Spinner>(R.id.difficultyDropdown)
        val difficulties = arrayOf("Easy", "Medium", "Hard")
        val difficultyAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, difficulties)
        difficultyDropdown.adapter = difficultyAdapter

        /*val typeDropdown = findViewById<Spinner>(R.id.typeDropdown)
        val types = arrayOf("Multiple Choice", "True or False")
        val typeAdapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, types)
        typeDropdown.adapter = typeAdapter*/

        val theButton = findViewById<Button>(R.id.theButton)
        theButton.setOnClickListener() {
            var amount :Int = editNumOfQs.text.toString().toInt()
            //println("Question Num is $amount")
            var category :Int = categoryDropdown.selectedItemPosition + 9
            //println("Category Num is $category")
            var difficulty :String = difficultyDropdown.selectedItem.toString().lowercase()
            //println("Difficulty is $difficulty")
            /*var type :String
            if (typeDropdown.selectedItemPosition == 0) {
                type = "multiple"
            } else {
                type = "boolean"
            }*/
            //println("Type is $type")

            val intent = Intent(this, TriviaQuizActivity::class.java)
            intent.putExtra("amount",amount)
            intent.putExtra("category",category)
            intent.putExtra("difficulty",difficulty)
            startActivity(intent)

            /*vm.getAllTriviaQuestions(amount, category, difficulty, "multiple")
            vm.triviaQuestions.observe(this) {
                println(it)
            }*/
        }
    }
}