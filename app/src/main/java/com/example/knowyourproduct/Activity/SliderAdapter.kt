package com.example.knowyourproduct.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.knowyourproduct.R

class SliderAdapter(private val context: Context, private val image_arraylist: ArrayList<String>):PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater).inflate(R.layout.slider_item, null)
        val im_slider = view.findViewById<ImageView>(R.id.image_id)
        Glide.with(context).load(image_arraylist[position]).into(im_slider)
        val vp = container as ViewPager
        vp.addView(view)

        return view
    }
    override fun getCount(): Int {

        return image_arraylist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }



}