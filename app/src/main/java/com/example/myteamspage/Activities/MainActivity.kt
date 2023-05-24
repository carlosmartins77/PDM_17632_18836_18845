package com.example.myteamspage.Activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.myteamspage.Entities.Team
import com.example.myteamspage.Adapters.MyTeamsPageAdapter
import com.example.myteamspage.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamList = findViewById<ListView>(R.id.myTeamX)

        val team1 = Team("Portugal", true, "Espanha","20:30", false)
        val team2 = Team("França", false, "Marrocos","20:30", false)
        val team3 = Team("Inglaterra", false, "Irlanda","20:30", true)
        val team4 = Team("Holanda", true, "Bélgica","20:30", true)

        val values = mutableListOf<Team>(team1, team2, team3, team4)

        var adapter = MyTeamsPageAdapter(this, R.layout.my_teams_layout, values)
        teamList.adapter = adapter

        val listViews = findViewById<ListView>(R.id.myTeamX)

        listViews.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val str = ( adapterView.adapter as MyTeamsPageAdapter).getItem(position)
            Toast.makeText(this,str.toString(), Toast.LENGTH_LONG).show()
        }

    }
}