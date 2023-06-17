package com.example.myteamspage.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.R

class PublicationAdapter(private val tweets: List<Publication>) :
    RecyclerView.Adapter<PublicationAdapter.TweetViewHolder>() {

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.avatarImage)
        val username: TextView = itemView.findViewById(R.id.usernameText)
        val tweetText: TextView = itemView.findViewById(R.id.tweetText)
        val commentIcon: ImageView = itemView.findViewById(R.id.commentIcon)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        val date: TextView = itemView.findViewById(R.id.tweetDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.publication_item, parent, false)
        return TweetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val currentTweet = tweets[position]
        // Set the data for each tweet item in the list
        holder.profileImage.setImageResource(currentTweet.avatarImage)
        holder.username.text = currentTweet.usernameText
        holder.tweetText.text = currentTweet.tweetText
        holder.commentIcon.setImageResource(currentTweet.commentIcon)
        holder.editIcon.setImageResource(currentTweet.editIcon)
        holder.date.text = currentTweet.date
    }

    override fun getItemCount() = tweets.size
}