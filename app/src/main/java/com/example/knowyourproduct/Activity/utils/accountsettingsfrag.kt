package com.example.knowyourproduct.Activity.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.knowyourproduct.Activity.Login
import com.example.knowyourproduct.R


class accountsettingsfrag : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_accountsettingsfrag, container, false)

        val tv = v.findViewById<TextView>(R.id.username)
        tv.text=Login.showUser().accountname



        return v
    }


}