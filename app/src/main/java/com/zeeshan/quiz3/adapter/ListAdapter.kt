package com.zeeshan.quiz3.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zeeshan.quiz3.R
import com.zeeshan.quiz3.model.User

class ListAdapter(var ctx : Context, var userList : ArrayList<User>): RecyclerView.Adapter<ListAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.user_card_row, null)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currUser = userList[position]
        holder.name.text = currUser.name
        holder.email.text = currUser.email

    }


    inner class CustomViewHolder(var view : View): RecyclerView.ViewHolder(view) {
       var name = view.findViewById<TextView>(R.id.user_name)
        var email = view.findViewById<TextView>(R.id.user_email)
    }
}