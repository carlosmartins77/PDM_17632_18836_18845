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
    val updatedAt: String
) {
    override fun toString(): String {
        return "SQLPublication(_id='$_id', username='$username', content='$content', createdAt='$createdAt', updatedAt='$updatedAt')"
    }
}

data class PublicationResponse(
    val message: List<PublicationImage>
)

data class PublicationImage(
    val _id: String,
    val profileImage: Int,
    val username: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String
) {
    override fun toString(): String {
        return "PublicationImage(_id='$_id', profileImage='$profileImage' username='$username', content='$content', createdAt='$createdAt', updatedAt='$updatedAt')"
    }
}