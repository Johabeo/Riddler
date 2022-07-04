package com.example.riddler.ui.view

import android.content.ClipDescription
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.riddler.R
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.QuizRepository
import com.example.riddler.ui.viewmodel.DiscoverViewModel
import com.example.riddler.ui.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class CreateQuizFragment : Fragment() {

    lateinit var createQuizButton: Button
    lateinit var editNumQuestions: EditText
    lateinit var categoryDropdown: AutoCompleteTextView
    lateinit var difficultyDropdown: AutoCompleteTextView
    lateinit var quizName: EditText
    lateinit var quizDescription: EditText
    lateinit var vm: QuizViewModel
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_quiz, container, false)
        bindViews(view)
        vm = ViewModelProvider(this).get(QuizViewModel::class.java)
        createAdapters()
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val createQuizButton = view.findViewById<Button>(R.id.createQuiz)
//        val vm = ViewModelProvider(this).get(QuizViewModel::class.java)
//        val editNumQuestions = view.findViewById<EditText>(R.id.editNumQuestions)
//        val categoryDropdown = view.findViewById<Spinner>(R.id.categoryDropdown)
//        val difficultyDropdown = view.findViewById<Spinner>(R.id.difficultyDropdown)
//        val quizName = view.findViewById<EditText>(R.id.editQuizName)
//        val quizDescription = view.findViewById<EditText>(R.id.editDescription)


        createQuizButton.setOnClickListener {
            Toast.makeText(activity, "Quiz Created", Toast.LENGTH_LONG).show()
            createQuiz()
        }

    }

    fun createAdapters() {
        val difficulties = resources.getStringArray(R.array.difficulty)
        val category = resources.getStringArray(R.array.category)

        val categoryAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(),
            R.layout.exposed_drop_item, category)
        val difficultyAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(),
            R.layout.exposed_drop_item, difficulties)

        categoryDropdown.setAdapter(categoryAdapter)
        difficultyDropdown.setAdapter(difficultyAdapter)
    }

    fun createQuiz() {
        var amount :Int = editNumQuestions.text.toString().toInt()
        //println("Question Num is $amount")
        var categoryNum = categoryDropdown.listSelection
        var categoryName = categoryDropdown.listSelection.toString()
        //println("Category Num is $category")
        var difficulty = difficultyDropdown.listSelection.toString()
        var quizNameText = quizName.text.toString()
        var quizDescriptiontText = quizDescription.text.toString()
        vm.createQuizFromApi(10, categoryNum, categoryName, difficulty, quizNameText, quizDescriptiontText)
    }

    fun bindViews(view: View) {
        with (view) {
            createQuizButton = findViewById<Button>(R.id.createQuiz)
            editNumQuestions = findViewById<EditText>(R.id.editNumQuestions)
            categoryDropdown = findViewById<AutoCompleteTextView>(R.id.categoryDropdown)
            difficultyDropdown = findViewById<AutoCompleteTextView>(R.id.difficultyDropdown)
            quizName = findViewById<EditText>(R.id.editQuizName)
            quizDescription = findViewById<EditText>(R.id.editDescription)
        }
    }

}