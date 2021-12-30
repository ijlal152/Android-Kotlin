package com.example.android_semester_project

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import com.example.android_semester_project.model.Reqres.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MyAdpt (private val dataList: MutableList<Date>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]

        val userFullname = holder.itemView.user_full_name
        val userAvatar = holder.itemView.user_avatar

        val fullName = "${data.firstname} ${data.lastName}"
        userFullname.text = fullName

        Picasso.get()
            .load(data.avatar)
            .into(userAvatar)
        holder.itemView.setOnClickListener{
            Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = dataList.size


}