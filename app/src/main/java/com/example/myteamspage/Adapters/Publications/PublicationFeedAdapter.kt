package com.example.myteamspage.Adapters.Publications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.PublicationAdapter
import com.example.myteamspage.Classes.PublicationImage
import com.example.myteamspage.R

class PublicationFeedAdapter(private val tweets: List<PublicationImage>) :
    RecyclerView.Adapter<PublicationFeedAdapter.TweetViewHolder>() {

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.avatarImage)
        val username: TextView = itemView.findViewById(R.id.usernameText)
        val tweetText: TextView = itemView.findViewById(R.id.tweetText)
        val tweetDate: TextView = itemView.findViewById(R.id.tweetDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.publication_feed_item, parent, false)
        return TweetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val currentTweet = tweets[position]
        holder.profileImage.setImageResource(R.drawable.baseline_image_24)
        holder.username.text = currentTweet.username
        holder.tweetText.text = currentTweet.content
        holder.tweetDate.text = currentTweet.createdAt
    }

    override fun getItemCount() = tweets.size
}