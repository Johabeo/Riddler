package com.example.riddler.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.data.model.Quiz
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardQuizListAdapter(val data: ArrayList<Quiz>, val onClick : (Int) -> Unit) : RecyclerView.Adapter<QuizItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_quiz_dashboard, parent, false)

        return QuizItemViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: QuizItemViewHolder, position: Int) {
        holder.title.setText(data[position].name)
        holder.description.setText(data[position].description)
        holder.category.setText(data[position].type)
        holder.difficulty.setText(data[position].difficulty)

//        when (holder.description.text) {
//            "General Knowledge" -> {
//                holder.R.id.imageView
//            }
//            "Books" -> {
//
//            }
//            "Film" -> {
//
//            }
//            "Music" -> {
//
//            }
//            "Musical & Theatres" -> {
//
//            }
//            "Television" -> {
//
//            }
//            "Video Games" -> {
//
//            }
//            "Board Games" -> {
//
//            }
//            "Science and Nature" -> {
//
//            }
//            "Computers" -> {
//
//            }
//            "Mathematics" -> {
//
//            }
//            "Mythology" -> {
//
//            }
//            "Sports" -> {
//
//            }
//            "Geography" -> {
//
//            }
//            "History" -> {
//
//            }
//            "Politics" -> {
//
//            }
//            "Art" -> {
//
//            }
//            "Celebrities" -> {
//
//            }
//            "Animals" -> {
//
//            }
//            "Vehicles" -> {
//
//            }
//            "Comics" -> {
//
//            }
//            "Gadgets" -> {
//
//            }
//            "Anime & Manga" -> {
//
//            }
//            "Cartoons & Animation" -> {
//
//            }
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class QuizItemViewHolder(itemView: View, val clickCallback: (Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    init {
        itemView.setOnClickListener(this)
    }

    val title = itemView.findViewById<TextView>(R.id.dash_quiz_listview_quizTitle)
    val description = itemView.findViewById<TextView>(R.id.dash_quiz_listview_quizDescription)
    val category = itemView.findViewById<TextView>(R.id.dash_quiz_listview_categoryText)
    val difficulty = itemView.findViewById<TextView>(R.id.dash_quiz_listview_difficultyText)
    val root = itemView.findViewById<ConstraintLayout>(R.id.dash_quiz_listitem_rootLayout)

    override fun onClick(p0: View?) {
        clickCallback(adapterPosition)
    }
}