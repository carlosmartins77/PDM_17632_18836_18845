package com.example.myteamspage.Adapters.Teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Classes.Teams
import com.example.myteamspage.R

class TeamsAdapter(private val teams: List<Teams>) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

        class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val team1: ImageView = itemView.findViewById(R.id.idteam1)
            val team2: ImageView = itemView.findViewById(R.id.idteam2)
            val date: TextView = itemView.findViewById(R.id.dateText)
            val idTeam1: TextView = itemView.findViewById(R.id.idteam1Text)
            val idTeam2: TextView = itemView.findViewById(R.id.idteam2Text)
            val location: TextView = itemView.findViewById(R.id.locationText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.game_list_of_teams, parent, false)
            return TeamsViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
            val currentGame = teams[position]
            holder.team1.setImageResource(R.drawable.arsenal)
            holder.team2.setImageResource(R.drawable.realmadrid)
            holder.date.text = currentGame.gameDateTime
            holder.idTeam1.text = currentGame.idTeam1
            holder.idTeam2.text = currentGame.idTeam2
            holder.location.text = currentGame.location
        }

        override fun getItemCount() = teams.size
    }