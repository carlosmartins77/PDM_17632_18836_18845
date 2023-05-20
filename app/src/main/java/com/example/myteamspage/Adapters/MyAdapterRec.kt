package com.example.myteamspage.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapterRec(private val mList: List<String>) : RecyclerView.Adapter<MyAdapterRecViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterRecViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyAdapterRecViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: MyAdapterRecViewHolder, position: Int) {
        val color = when(position % 2) {
            0 -> android.R.color.holo_red_dark
            1 -> android.R.color.holo_blue_dark
            else -> android.R.color.holo_orange_dark
        }
        val text = mList.get(position) ?: "????"
        holder.bindData(text,color)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}