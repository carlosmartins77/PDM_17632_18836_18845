package com.example.myteamspage.Classes

data class TeamsResponse(
    val message: List<Teams>
)
data class Teams(
    val _id: String,
    val username: String,
    val idTeam1: String,
    val idTeam2: String,
    val location: String,
    val gameDateTime: String

) {
    override fun toString(): String {
        return "Teams_Games(_id='$_id', username='$username', idTeam1='$idTeam1', idTeam2='$idTeam2', location='$location', gameDateTime='$gameDateTime')"
    }
}

