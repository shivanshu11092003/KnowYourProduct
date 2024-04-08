package com.example.knowyourproduct.Activity


import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.Model.UserUpload
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.FragmentPostBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso
import java.security.PublicKey


class Post : BottomSheetDialogFragment() {

//    private lateinit var auth: FirebaseAuth
//    private lateinit var databaseReference: DatabaseReference
//    private lateinit var storageReference : StorageReference
//    private lateinit var imageUri : Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view =inflater.inflate(R.layout.fragment_post,container,false)
        //Upload
//        this.binding.selectImage.setOnClickListener{
//            val intent = Intent()
//            intent.type ="image/*"
//            intent.action=Intent.ACTION_PICK
//            imageLauncher.launch(intent)
//        }
//        auth = FirebaseAuth.getInstance()
//
//        binding.userName.text = Login.showUser().accountname
//        Picasso.get().load(Login.showUser().profileimage).into(binding.userPic)
//
//
//        binding.buttontoPost.setOnClickListener{
//            wholedataupload()
//        }

        return view

    }

//    private fun uploadpic() {
//        imageUri = Uri.parse("android.resource://${requireContext().packageName}/${R.drawable.unnamed}")
//        storageReference =FirebaseStorage.getInstance().getReference("Accounts")
//        storageReference.putFile(imageUri).addOnSuccessListener {
//            Toast.makeText(requireContext(),"success",Toast.LENGTH_SHORT).show()
//            // Clear the caption text
//            binding.captionid.setText("")
//
//            // Reset the image view to its initial state
//            binding.postPic.setImageDrawable(null)
//
//        }.addOnFailureListener{
//            Toast.makeText(requireContext(),"Fail",Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//    val imageLauncher=
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        if (it.resultCode == Activity.RESULT_OK){
//            if(it.data != null){
//                val ref = FirebaseDatabase.getInstance().getReference("Accounts")
//                ref.putFile(it.data!!.data!!).addOnSuccessListener{
//                    ref.downloadUrl.addOnSuccessListener {
//                        val username = binding.userName.text.toString()
//                        Firebase.database.reference.child(username).push().setValue(it.toString())
//                         val imageaddress=it.toString()
//
//
//                        Picasso.get().load(imageaddress).into(binding.postPic)
//
//                    }
//
//                }
//            }
//        }
//    }
//
//
//
//    fun wholedataupload(){
//        val username = binding.userName.text.toString()
//
//        val caption = binding.captionid.text.toString()
//        val imageaddress = R.id.post_pic.toString()
//        databaseReference =FirebaseDatabase.getInstance().getReference("Accounts")
//        databaseReference.child(username)
//        val user =  UserUpload(username,imageaddress,caption)
//        val uid = auth.currentUser?.uid
//        if (uid!=null){
//            databaseReference.child(username).setValue(user).addOnCompleteListener {
//                if (it.isSuccessful){
//                    uploadpic()
//                }
//            }
//        }
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


}