package com.example.knowyourproduct.Activity



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.knowyourproduct.Model.Post

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.knowyourproduct.R
import com.smarteist.autoimageslider.SliderView


class Home : Fragment() {
    lateinit var recyclerview : RecyclerView
    lateinit var postArrayLists: ArrayList<Post>

    lateinit var imageUrl: ArrayList<String>

    // on below line we are creating
    // a variable for our slider view.
    lateinit var sliderView: SliderView

    // on below line we are creating
    // a variable for our slider adapter.
    lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_home,container,false)





        recyclerview=view.findViewById(R.id.recyclerid)

        val caption = arrayOf(
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",
            "This product have high nutrient value and good for health.",

            )
        val profilepic = arrayOf(
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,
            R.drawable.unnamed,


            )
        val accountname = arrayOf(
            "Account Name 1",
            "Account Name 2",
            "Account Name 3",
            "Account Name 4",
            "Account Name 5",
            "Account Name 6",
            "Account Name 7",
            "Account Name 8",
            "Account Name 9",
            "Account Name 10",
            "Account Name 11",
            "Account Name 12",
            "Account Name 13"

        )

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        postArrayLists = arrayListOf<Post>()
        for (index in caption.indices) {
            val post = Post(
                caption[index],
                profilepic[index],
                accountname[index]
            )
            postArrayLists.add(post)
        }


        var myAdapter = chatRecyclerViewAdapter(postArrayLists, this@Home)
        recyclerview.adapter = myAdapter
        return view


    }






}











