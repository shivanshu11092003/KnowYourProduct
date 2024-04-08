package com.example.knowyourproduct.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.knowyourproduct.Model.Post
import com.example.knowyourproduct.R
import com.google.android.material.imageview.ShapeableImageView


class chatRecyclerViewAdapter(var postArrayList: ArrayList<Post>, var context: Home) :
    RecyclerView.Adapter<chatRecyclerViewAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.activity_eachpost,parent,false)
        return ViewHolder(itemview)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentitem = postArrayList[position]
        holder.accountname.text = currentitem.Account_name
        holder.profilepic.setImageResource(currentitem.profile_pic)
        holder.caption.text = currentitem.caption




    }
    override fun getItemCount(): Int {
        return postArrayList.size

    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val profilepic = itemview.findViewById<ShapeableImageView>(R.id.user_pic)
        val accountname = itemview.findViewById<TextView>(R.id.user_name)

        val caption = itemview.findViewById<TextView>(R.id.description)

    }
}