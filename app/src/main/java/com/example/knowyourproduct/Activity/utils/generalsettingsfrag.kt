package com.example.knowyourproduct.Activity.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.knowyourproduct.Activity.Login
import com.example.knowyourproduct.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class generalsettingsfrag : Fragment() {
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_generalsettingsfrag, container, false)

            val tv = v.findViewById<TextView>(R.id.username)
            tv.text=Login.showUser().accountname





        return v
    }

}