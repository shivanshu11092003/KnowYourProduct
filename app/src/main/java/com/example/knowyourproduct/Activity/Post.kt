package com.example.knowyourproduct.Activity



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

import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso


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
        Picasso.get().load(Login.showUser().profileimage).into(binding.userPic)
        binding.userName.text = Login.showUser().accountname


        binding.Uploadbtn.setOnClickListener {
            if (binding.textInputLayout.editText?.text.toString().equals("") || imageUrl.equals(null)) {
                Toast.makeText(
                        requireContext(),
                        "Please provide caption",
                        Toast.LENGTH_SHORT
                    ).show()


            }else{

                val post = uploadPost(imageUrl, binding.textInputLayout.editText?.text.toString(),Login.showUser().accountname,
                    System.currentTimeMillis().toString(),Login.showUser().profileimage)


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