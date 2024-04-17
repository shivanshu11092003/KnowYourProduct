package com.example.knowyourproduct.Activity.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
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
        val darkModeSwitch = v.findViewById<Switch>(R.id.darkModeSwitch)

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
//            activity?.recreate() // Recreate the activity to apply theme changes
        }






        return v
    }

}