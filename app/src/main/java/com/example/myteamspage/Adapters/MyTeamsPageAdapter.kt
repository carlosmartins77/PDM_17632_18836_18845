package com.example.myteamspage.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myteamspage.Entities.Team
import com.example.myteamspage.R

class MyTeamsPageAdapter(context: Context, resource: Int, objects: MutableList<Team> ):
    ArrayAdapter<Team>(context, resource, objects) {
        var mContext: Context
        var mValues:MutableList<Team>
        var mResource: Int

    init {
        mContext = context
        mValues = objects
        mResource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(mContext).inflate(mResource, parent, false)


        val team1Name = rowView.findViewById<TextView>(R.id.teamName)
        val team1Image = rowView.findViewById<ImageView>(R.id.teamImage)
        val gameTime = rowView.findViewById<TextView>(R.id.gameTime)
        val team2Name = rowView.findViewById<TextView>(R.id.opponentName)
        val team2Image = rowView.findViewById<ImageView>(R.id.opponentImage)


        val value = mValues[position]

        team1Name.text = value.name
        gameTime.text = value.nextOpponentTime
        team2Name.text = value.nextOpponent


        if(value.image){
            team1Image.setBackgroundColor(Color.rgb(0, 0, 255))
        }
        else {
            team1Image.setBackgroundColor(Color.rgb(255, 0, 0))
        }

        if(value.nextOpponentImage){
            team2Image.setBackgroundColor(Color.rgb(0, 0, 255))
        }
        else {
            team2Image.setBackgroundColor(Color.rgb(255, 0, 0))
        }

        return rowView

    }


}