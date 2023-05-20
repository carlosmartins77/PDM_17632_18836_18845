package com.example.myteamspage.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.R

class MyAdapterRecViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_game_layout, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.layout_item_description)
    private var im1: ImageView? = itemView.findViewById(R.id.layout_item_team1_id)
    private var im2: ImageView? = itemView.findViewById(R.id.layout_item_team2_id)
    fun bindData(text: String, colorResource: Int) {
        tv?.text = text
        im1?.setBackgroundResource(colorResource)
        im2?.setBackgroundResource(colorResource)
        itemView.setOnClickListener {
            Toast.makeText(parent.context,text,Toast.LENGTH_LONG).show()
        }
    }
}