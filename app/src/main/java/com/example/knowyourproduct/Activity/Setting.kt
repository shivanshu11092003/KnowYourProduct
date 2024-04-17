package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.knowyourproduct.Activity.utils.generalsettingsfrag
import com.example.knowyourproduct.R


class Setting : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_setting, container, false)
        var a =v.findViewById<TextView>(R.id.general_settings)
        var b =v.findViewById<TextView>(R.id.account_settings)
        var c =v.findViewById<TextView>(R.id.data_settings)
        var d =v.findViewById<TextView>(R.id.other_settings)

        a.setOnClickListener{

            repalcefrag(generalsettingsfrag())
        }

        return v

    }
    private fun repalcefrag(fragment: Fragment){
        val fragtransaction=childFragmentManager.beginTransaction()
        fragtransaction.replace(R.id.mainframeid, fragment)
        fragtransaction.commit()
    }

}