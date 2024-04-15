package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowyourproduct.Model.UserUpload
import com.example.knowyourproduct.Model.uploadPost

import com.example.knowyourproduct.databinding.FragmentAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso


class Account : Fragment() {


   private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =FragmentAccountBinding.inflate(inflater,container,false)
        val postList = ArrayList<uploadPost>()
        val adapter = AccountAdapter(requireContext(),postList)
        binding.recycleridAccount.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleridAccount.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val tempList = arrayListOf<uploadPost>()
            for(i in it.documents){
                val post:uploadPost = i.toObject<uploadPost>()!!
                tempList.add(post)


            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()

        }
        Picasso.get().load(Login.showUser().profileimage).into(binding.imageID)
        binding.accountnameid.text= Login.showUser().accountname
        binding.emailNavholder.text = Login.showUser().email

        return binding.root
    }




}