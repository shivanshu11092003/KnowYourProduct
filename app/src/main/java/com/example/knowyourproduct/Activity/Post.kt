package com.example.knowyourproduct.Activity



import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.knowyourproduct.Activity.utils.POST
import com.example.knowyourproduct.Activity.utils.POST_FOLDER

import com.example.knowyourproduct.Activity.utils.uploadImage
import com.example.knowyourproduct.Model.UserUpload
import com.example.knowyourproduct.Model.uploadPost
import com.example.knowyourproduct.databinding.FragmentPostBinding
import com.google.firebase.Firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage


class Post : Fragment() {

    private lateinit var _binding: FragmentPostBinding
    private val binding get() = _binding
    private lateinit var user : UserUpload


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        binding.selectuploadpic.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.Uploadbtn.setOnClickListener {
            if (binding.textInputLayout.editText?.text.toString().equals("")) {
                Toast.makeText(requireContext(), "Please provide caption", Toast.LENGTH_SHORT).show()
            }else{
                val post = uploadPost(imageUrl, binding.textInputLayout.editText?.text.toString())
                Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }


        return binding.root

    }

    lateinit var imageUrl:String
    private var  launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri ->
        uri?.let {
        uploadImage(uri , POST_FOLDER) {
            url ->
            if ( it != null){

                binding.selectuploadpic.setImageURI(uri)
                imageUrl= url!!



            }

        }
    }
    }




}