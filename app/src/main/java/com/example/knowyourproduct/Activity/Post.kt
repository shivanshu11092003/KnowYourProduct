package com.example.knowyourproduct.Activity

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.knowyourproduct.databinding.FragmentPostBinding



class Post : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    var fileUri : Uri? = null
    fun loadBitmapFromUri(contentResolver: ContentResolver, fileUri: Uri): Bitmap? {
        return MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding=FragmentPostBinding.inflate(inflater,container,false)
        binding.selectImage.setOnClickListener{
            val intent = Intent()
            intent.type ="image/*"
            intent.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent,"Choose Image to Upload"),0
            )


        }

        return binding.root





    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 0 && requestCode == RESULT_OK && data!=null && data.data != null){
//            fileUri = data.data
//            try {
//                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
//                image_view.setImageBitmap(bitmap)
//
//            } catch (e: Exception) {
//                Log.e("Exception", "Error: " + e)
//            }
//
//        }
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}