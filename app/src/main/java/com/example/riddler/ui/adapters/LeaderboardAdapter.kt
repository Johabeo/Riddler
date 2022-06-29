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
import com.example.riddler.data.model.Player

class LeaderboardAdapter(private var playerList : List<Player>) : RecyclerView.Adapter<LeaderboardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        // inflate
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.leaderboard_card, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        // add element to view holder
        val player = playerList[position]
        holder.playerName.text = player.playerName
        holder.playerScore.text = player.score.toString()
    }

    override fun getItemCount(): Int {
        // size of the list/datasource
        return playerList.size
    }
}

class LeaderboardViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val playerName : TextView = view.findViewById(R.id.leaderboardPlayerName)
    val playerScore : TextView = view.findViewById(R.id.leaderboardPlayerScore)
}