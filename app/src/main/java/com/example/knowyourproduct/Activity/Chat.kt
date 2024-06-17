package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.FragmentChatBinding
import com.example.knowyourproduct.databinding.FragmentPostBinding


class Chat : Fragment() {
    private lateinit var _binding: FragmentChatBinding
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return _binding.root
    }


}