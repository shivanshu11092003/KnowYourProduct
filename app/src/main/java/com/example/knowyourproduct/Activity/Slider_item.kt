package com.example.knowyourproduct.Activity

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.knowyourproduct.R

class Slider_item : AppCompatActivity() {
    private lateinit var mImg: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.slider_item)
        val url = intent.getStringExtra("url")
        mImg = findViewById(R.id.image_id)
        url?.let {
            Glide.with(this).load(it).into(mImg)
        }

    }
}