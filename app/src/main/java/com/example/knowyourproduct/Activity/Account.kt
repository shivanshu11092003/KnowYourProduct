package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.FragmentAccountBinding


class Account : Fragment() {


   private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }


}