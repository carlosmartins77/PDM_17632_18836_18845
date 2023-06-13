package com.example.myteamspage.Adapters.Account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Classes.Account

class AdapterRec(private val mList: List<Account>) : RecyclerView.Adapter<AdapterRecViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AdapterRecViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: AdapterRecViewHolder, position: Int) {
        val photo = mList.get(position).optionImage
        val text = mList.get(position).optionName
        holder.bindData(text,photo)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}