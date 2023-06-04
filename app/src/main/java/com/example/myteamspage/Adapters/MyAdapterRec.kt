package com.example.myteamspage.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Classes.Games
import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.R

class MyAdapterRec(private val mList: List<Games>) : RecyclerView.Adapter<MyAdapterRec.GamesViewHolder>() {
    class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val team1: ImageView = itemView.findViewById(R.id.layout_item_team1_id)
        val description: TextView = itemView.findViewById(R.id.layout_item_description)
        val team2: ImageView = itemView.findViewById(R.id.layout_item_team2_id)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_game_layout, parent, false)
        return GamesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentGame = mList[position]
        Log.d("Carlos", mList[0].toString() )
        // Set the data for each game item in the list
        holder.team1.setImageResource(currentGame.imageTeam1)
        holder.description.text = currentGame.description
        Log.d("Description",currentGame.description )
        holder.team2.setImageResource(currentGame.imageTeam2)
    }
    override fun getItemCount() = mList.size
}

