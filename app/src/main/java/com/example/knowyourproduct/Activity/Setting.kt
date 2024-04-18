package com.example.knowyourproduct.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.example.knowyourproduct.Activity.utils.accountsettingsfrag
import com.example.knowyourproduct.Activity.utils.datasettings
import com.example.knowyourproduct.Activity.utils.generalsettingsfrag
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.ActivityMainBinding
import com.example.knowyourproduct.databinding.FragmentSearchBinding
import com.example.knowyourproduct.databinding.FragmentSettingBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.squareup.picasso.Picasso


class Setting : Fragment() {
    lateinit var binding : FragmentSettingBinding
    lateinit var binding1: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater,container,false)

        Picasso.get().load(Login.showUser().profileimage).into(binding.profilepic)
        binding.username.text = Login.showUser().accountname
        binding.emailid.text = Login.showUser().email
        binding.backbtn.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }

        binding.tvlogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireContext(), Login ::class.java))

            val headerview = binding1.navView.getHeaderView(0)
          val headerpic=headerview.findViewById<ShapeableImageView>(R.id.imageID)
         val headeraccountname = headerview.findViewById<TextView>(R.id.accountnameid)
        val headeremail =headerview.findViewById<TextView>(R.id.email_navholder)
           headeraccountname.text="Please Login "
           headerpic.setImageResource(R.drawable.unnamed)
          headeremail.text="--"
        }
        return binding.root

    }


}