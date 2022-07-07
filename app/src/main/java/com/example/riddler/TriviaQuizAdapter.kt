package com.example.riddler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TriviaQuizAdapter(private var triviaList : List<TriviaQuestions.Question>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trivia_list_item, parent, false)
        return ViewHolder(view)
    }

    fun setItems(triviaList: List<TriviaQuestions.Question>){
        this.triviaList = triviaList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // add element to view holder
        val itemVM = triviaList[position]
        holder.question.text = itemVM.question
        holder.correctAnswer.text = itemVM.correct_answer

        var context = holder.card.getContext()
        var incorrectAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemVM.incorrect_answers!!)
        holder.incorrectList.adapter = incorrectAdapter
    }

    override fun getItemCount(): Int {
        // size of the list/datasource
        return triviaList.size
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val card : CardView = view.findViewById(R.id.questionCard)
    val question : TextView = view.findViewById(R.id.questionText)
    val correctAnswer : TextView = view.findViewById(R.id.correctText)
    val incorrectList : ListView = view.findViewById(R.id.incorrectList)
}