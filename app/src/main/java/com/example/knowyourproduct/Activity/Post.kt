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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso


class Post : Fragment() {

    private lateinit var _binding: FragmentPostBinding
    private val binding get() = _binding
    private lateinit var uploadTask : UploadTask
    var imageUrl:String = ""


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
            if (binding.textInputLayout.editText?.text.toString().isEmpty() || imageUrl == null) {
                if (binding.textInputLayout.editText?.text.toString().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please provide caption",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (imageUrl == null) {
                    // Remove the image view
                    binding.selectuploadpic.setImageURI(null)
                    Toast.makeText(
                        requireContext(),
                        "Please provide Picture",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }else{

                val post = uploadPost(imageUrl, binding.textInputLayout.editText?.text.toString(),Login.showUser().accountname,
                    System.currentTimeMillis().toString(),Login.showUser().profileimage,Login.showUser().email)


                Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {


                        }

                }
            }

        }


        return binding.root

    }


    private var  launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri ->
        uri?.let {
            uploadImage(uri , POST_FOLDER) {
            url ->
            url?.let {



                binding.selectuploadpic.setImageURI(uri)
                imageUrl= url

                val uploadTask = FirebaseStorage.getInstance().getReference(POST_FOLDER)
                    .putFile(uri)
                uploadTask.addOnProgressListener {taskSnapshot ->

                    val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                    val percentage = (progress.toDouble() / 100.0 * 100).toInt()
                    Toast.makeText(requireContext(), "Upload progress: $percentage%", Toast.LENGTH_SHORT).show()

                }



            }?: run {

                Toast.makeText(requireContext(), "Failed to upload image", Toast.LENGTH_SHORT).show()
            }



            }




    }
    }




}