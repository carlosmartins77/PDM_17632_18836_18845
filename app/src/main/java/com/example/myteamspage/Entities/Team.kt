package com.example.myteamspage.Entities

class Team(val name: String, var image:Boolean, var nextOpponent:String , var nextOpponentTime:String, var nextOpponentImage:Boolean) {
    init {
        println("Created team $name with image $image. Next game: $nextOpponent at $nextOpponentTime");
    }
}