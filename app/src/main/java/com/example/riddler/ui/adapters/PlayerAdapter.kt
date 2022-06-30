package com.example.riddler.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.riddler.R
import com.example.riddler.TriviaQuestions
import com.example.riddler.data.model.LobbyPlayers

class PlayerAdapter(private var playerList : List<LobbyPlayers>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // add element to view holder
        holder.playerName.text = playerList[position].playerName

    }

    override fun getItemCount(): Int {
        // size of the list/datasource
        return playerList.size
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val playerName : TextView = view.findViewById(R.id.playerName)
}