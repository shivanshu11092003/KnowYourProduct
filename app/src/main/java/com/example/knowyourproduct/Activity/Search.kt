package com.example.knowyourproduct.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knowyourproduct.Activity.utils.User_Node
import com.example.knowyourproduct.Model.GoogleDetails
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.knowyourproduct.Activity.utils.POST
import com.example.knowyourproduct.Model.uploadPost
import com.example.knowyourproduct.R

import com.example.knowyourproduct.databinding.FragmentSearchBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import java.util.Locale


class Search : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    lateinit var searchView : SearchView

    private  var postList = ArrayList<GoogleDetails>()
    private  var postList2 = ArrayList<uploadPost>()
    private lateinit var  myAdapter: SearchAdapter
    private lateinit var  myAdapter2: chatRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        searchView = binding.SearchView
//        binding.recycleridpost
//            .visibility = View.INVISIBLE
//        binding.materialCardView.setOnClickListener{
//            binding.recycleridpost.visibility = View.VISIBLE
//        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtertext(newText)
                filtertext2(newText)
                return true
            }

        })



        myAdapter = SearchAdapter(requireContext(),postList)
        myAdapter2 = chatRecyclerViewAdapter(postList2,requireContext())

        binding.recycleridpost.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recycleridSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleridSearch.adapter= myAdapter
        binding.recycleridpost.adapter= myAdapter2
        val db = FirebaseFirestore.getInstance()
        val collectionref = db.collection(User_Node)
        collectionref.get().addOnSuccessListener { documents ->
            val tempList = arrayListOf<GoogleDetails>()

            for (i in documents) {

                val accountname = i.getString("accountname")
                val profileimage = i.getString("profileimage")
                val email = i.getString("email")
                val google = GoogleDetails(
                    accountname.toString(), profileimage.toString(),
                    email.toString()
                )

                tempList.add(google)


            }
            postList.addAll(tempList)
            myAdapter.notifyDataSetChanged()



        }

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            val tempList = arrayListOf<uploadPost>()
            for(i in it.documents){
                val post:uploadPost = i.toObject<uploadPost>()!!
                tempList.add(post)


            }
            postList2.addAll(tempList)
            myAdapter2.notifyDataSetChanged()

        }
        return binding.root


}
    private fun filtertext(query : String?){
        val filteredList = ArrayList<GoogleDetails>()
        if (query != null) {
            for (i in postList) {
                if (i.accountname.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
//                Toast.makeText(requireContext(),"No Such User", Toast.LENGTH_SHORT).show()
            } else {
                myAdapter.setFilteredList(filteredList)
            }

        }

    }
    private fun filtertext2(query : String?){
        val filteredList2 = ArrayList<uploadPost>()
        if (query != null) {
            for (i in postList2) {
                if (i.caption!!.lowercase(Locale.ROOT).contains(query)) {
                    filteredList2.add(i)

                }

            }
            if (filteredList2.isEmpty()) {

                Toast.makeText(requireContext(),"No such post", Toast.LENGTH_SHORT).show()

            } else {
                myAdapter2.setFilteredList(filteredList2)


            }

        }

    }
}