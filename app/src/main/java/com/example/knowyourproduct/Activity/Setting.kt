package com.example.knowyourproduct.Activity

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
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


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
        var profilepic = v.findViewById<ShapeableImageView>(R.id.profilepic)
        Picasso.get().load(Login.showUser().profileimage).into(profilepic)


//        var a =v.findViewById<TextView>(R.id.general_settings)
//        var b =v.findViewById<TextView>(R.id.account_settings)
//        var c =v.findViewById<TextView>(R.id.data_settings)
//        var d =v.findViewById<TextView>(R.id.other_settings)
//
//        a.setOnClickListener{
//            a.isGone = true
//            b.isGone = true
//            c.isGone = true
//            d.isGone = true
//            replacefrag(generalsettingsfrag())
//
//
//
//        }
//        b.setOnClickListener{
//            a.isGone = true
//            b.isGone = true
//            c.isGone = true
//            d.isGone = true
//            replacefrag(accountsettingsfrag ())
//
//        }
//
//        c.setOnClickListener{
//            a.isGone = true
//            b.isGone = true
//            c.isGone = true
//            d.isGone = true
//            replacefrag(datasettings())
//        }

        return v

    }
    private fun replacefrag(fragment: Fragment){
        val fragtransaction=childFragmentManager.beginTransaction()

        fragtransaction.replace(R.id.changefrag, fragment)
        fragtransaction.addToBackStack(null)
        fragtransaction.commit()
    }


}