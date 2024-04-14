package com.example.knowyourproduct.Activity

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.knowyourproduct.Model.Post
import com.example.knowyourproduct.R
import com.google.android.material.imageview.ShapeableImageView
import com.smarteist.autoimageslider.SliderView


class chatRecyclerViewAdapter(var postArrayList: ArrayList<Post>,var context: Context) :
    RecyclerView.Adapter<chatRecyclerViewAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.activity_eachpost,parent,false)
            return ViewHolder(itemview,)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentitem = postArrayList[position]
        holder.accountname.text = currentitem.Account_name
        holder.profilepic.setImageResource(currentitem.profile_pic)
        holder.caption.text = currentitem.caption
        val sliderImageList = ArrayList<String>()

        sliderImageList.add("https://oi65.photobucket.com/albums/h214/L_The_Legend/DeathNoteS01E09E.png")
        sliderImageList.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/DAngel.png")
        sliderImageList.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/C_D_A.png")
        sliderImageList.add("https://oi49.photobucket.com/albums/f260/starfoxfan/fursona.jpg")
        val sliderPagerAdapter = SliderAdapter(context,sliderImageList)
        holder.mViewPager.adapter = sliderPagerAdapter

        val dots = arrayOfNulls<TextView>(sliderImageList.size)
        addBottomDots(0, dots, holder)
        holder.mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                addBottomDots(position, dots, holder)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })






    }
    private fun addBottomDots(currentPage: Int, dots: Array<TextView?>, holder: ViewHolder) {
        holder.ll_dots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(holder.itemView.context)
            dots[i]?.text = Html.fromHtml("&#8226;")
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(Color.parseColor("#000000"))
            holder.ll_dots.addView(dots[i])
        }
        if (dots.isNotEmpty()) dots[currentPage]?.setTextColor(Color.parseColor("#FFFFFF"))
    }

    override fun getItemCount(): Int {
        return postArrayList.size

    }
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val profilepic = itemview.findViewById<ShapeableImageView>(R.id.user_pic)
        val accountname = itemview.findViewById<TextView>(R.id.user_name)

        val caption = itemview.findViewById<TextView>(R.id.description)

        val mViewPager= itemView.findViewById<ViewPager>(R.id.vp_slider)

        val ll_dots = itemView.findViewById<ViewGroup>(R.id.ll_dots)

    }
}