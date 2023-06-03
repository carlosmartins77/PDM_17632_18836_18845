package com.example.myteamspage.Adapters.Account

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.General
import com.example.myteamspage.Activities.Account.Notification
import com.example.myteamspage.Activities.Account.PersonalInfo
import com.example.myteamspage.Activities.Account.Security
import com.example.myteamspage.R

class AdapterRecViewHolder(inflater: LayoutInflater, val parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_list_account, parent, false)) {
    private var tv: TextView? = itemView.findViewById(R.id.layout_item_name_tv)
    private var iv: ImageView? = itemView.findViewById(R.id.layout_item_photo_iv)
    fun bindData(text: String, colorResource: Int) {
        tv?.text = text
        iv?.setBackgroundResource(colorResource)
        itemView.setOnClickListener {
            val context = parent.context.applicationContext
            if (text == "Personal Info"){
                val intent = Intent(context, PersonalInfo::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            else if(text == "Notifications"){
                val intent = Intent(context, Notification::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }else if(text == "General"){
                val intent = Intent(context, General::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else if(text == "Security"){
                val intent = Intent(context, Security::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else if(text == "Logout"){
                //adicionar o alerta
            }

        }
    }
}