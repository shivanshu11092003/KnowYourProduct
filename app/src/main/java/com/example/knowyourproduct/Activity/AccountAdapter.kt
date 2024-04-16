package com.example.knowyourproduct.Activity


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.knowyourproduct.Model.uploadPost
import com.example.knowyourproduct.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList


class AccountAdapter(var context: Context, var postList: ArrayList<uploadPost>):
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ShapeableImageView>(R.id.postimage)
        val caption = itemview.findViewById<TextView>(R.id.description)
        val profilepic = itemview.findViewById<ShapeableImageView>(R.id.user_pic)
        val accountname = itemview.findViewById<TextView>(R.id.user_name)
        val likepic = itemview.findViewById<ImageButton>(R.id.like)
        val dislikepic = itemview.findViewById<ImageButton>(R.id.dislike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.accountpost,parent,false)
        return ViewHolder(itemview)

    }

    override fun getItemCount(): Int {
        return postList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(postList.get(position).postUrl).into(holder.image)
        holder.caption.text = postList.get(position).caption
        Picasso.get().load(Login.showUser().profileimage).into(holder.profilepic)
        holder.accountname.text = Login.showUser().accountname
        holder.likepic.setOnClickListener{
            holder.likepic.setImageResource(R.drawable.favorite)
            holder.dislikepic.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        holder.dislikepic.setOnClickListener{
            holder.likepic.setImageResource(R.drawable.baseline_favorite_border_24)
            holder.dislikepic.setImageResource(R.drawable.favorite)

        }



    }


}