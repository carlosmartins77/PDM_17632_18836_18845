package com.example.myteamspage.Classes

class Publication(
    val avatarImage: Int,
    val usernameText: String,
    val tweetText: String,
    val commentIcon: Int,
    val editIcon: Int
)

data class SQLPublication(
    val _id: String,
    val username: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
) {
    override fun toString(): String {
        return "SQLPublication(_id='$_id', username='$username', content='$content', createdAt='$createdAt', updatedAt='$updatedAt', __v=$__v)"
    }
}

class PublicationItem(
    val username: String,
    val content: String,
    val date: String
)