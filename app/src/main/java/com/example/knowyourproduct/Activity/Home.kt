package com.example.knowyourproduct.Activity



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowyourproduct.Activity.utils.POST
import com.example.knowyourproduct.Model.uploadPost
import com.example.knowyourproduct.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val tempList = arrayListOf<uploadPost>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val postList = ArrayList<uploadPost>()
        val adapter = chatRecyclerViewAdapter(postList, requireContext())
        binding.recyclerid.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerid.adapter = adapter

        Firebase.firestore.collection(POST).orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener {

                for (i in it.documents) {
                    val post: uploadPost = i.toObject<uploadPost>()!!

                    tempList.add(post)


                }
                postList.addAll(tempList)
                adapter.notifyDataSetChanged()




            }
        binding.swipetorefresh.setOnRefreshListener {
            postList.clear()
            postList.addAll(tempList)
            binding.recyclerid.adapter!!.notifyDataSetChanged()
            binding.swipetorefresh.isRefreshing = false

        }


        return binding.root
    }


    }

















