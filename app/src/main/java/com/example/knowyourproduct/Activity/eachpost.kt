package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.knowyourproduct.R
import com.smarteist.autoimageslider.SliderView

class eachpost : AppCompatActivity() {
    lateinit var imageUrl: ArrayList<String>

    // on below line we are creating
    // a variable for our slider view.
    lateinit var sliderView: SliderView

    // on below line we are creating
    // a variable for our slider adapter.
    lateinit var sliderAdapter: SliderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eachpost)
        // Auto image slider start
        sliderView=findViewById(R.id.imageSlider)
        imageUrl = ArrayList()

        // on below line we are adding data to our image url array list.
        val imageUrl = arrayListOf(
            R.drawable.c4g041cfha821,
            R.drawable.c4g041cfha821,
            R.drawable.c4g041cfha821

        )


        // on below line we are initializing our
        // slider adapter and adding our list to it.
        sliderAdapter = SliderAdapter(imageUrl,this@eachpost)

        // on below line we are setting auto cycle direction
        // for our slider view from left to right.
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

        // on below line we are setting adapter for our slider.
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.scrollTimeInSec = 2

        // on below line we are setting auto cycle
        // to true to auto slide our items.
        sliderView.isAutoCycle = true

        // on below line we are calling start
        // auto cycle to start our cycle.
        sliderView.startAutoCycle()

        //imageslider end
    }
}