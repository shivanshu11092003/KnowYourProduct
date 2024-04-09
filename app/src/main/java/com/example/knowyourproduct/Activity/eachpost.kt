package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.EachpostBinding
import com.smarteist.autoimageslider.SliderView

class eachpost : AppCompatActivity() {

    private lateinit var binding: EachpostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=EachpostBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}