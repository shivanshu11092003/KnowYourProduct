package com.example.knowyourproduct.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.knowyourproduct.Activity.utils.FOLLOW
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class SearchAdapter(var context: Context, var postList: ArrayList<GoogleDetails>):
        RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    fun setFilteredList(product: ArrayList<GoogleDetails>){
        this.postList = product
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.search_eachitem,parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        var isFollow = false
        holder.accountname.text = postList.get(position).accountname
        Glide.with(context).load(postList.get(position).profileimage).placeholder(R.drawable.unnamed).into(holder.profilepic)
        Firebase.firestore.collection(Firebase.auth.currentUser!!.email + FOLLOW).whereEqualTo("email",postList
            .get(position).email).get().addOnSuccessListener {
                if (it.documents.size == 0){
                    isFollow = false

                }else{
                    holder.followbtn.text = "UnFollow"
                    isFollow = true
                }
        }
        holder.followbtn.setOnClickListener{
            if (isFollow) {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.email + FOLLOW).whereEqualTo("email",postList
                    .get(position).email).get().addOnSuccessListener {
                   Firebase.firestore.collection(Firebase.auth.currentUser!!.email + FOLLOW)
                       .document(it.documents.get(0).id).delete().addOnSuccessListener {
                           holder.followbtn.text = "Follow"
                           isFollow = false
                       }

                }

            }else{
                Firebase.firestore.collection(Firebase.auth.currentUser!!.email + FOLLOW).document()
                    .set(postList.get(position)).addOnSuccessListener {
                        holder.followbtn.text = "UnFollow"
                        isFollow = true
                    }
            }
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val profilepic = itemview.findViewById<ShapeableImageView>(R.id.user_pic)
        val accountname = itemview.findViewById<TextView>(R.id.user_name)
        val followbtn = itemview.findViewById<Button>(R.id.followbtn)

    }

}