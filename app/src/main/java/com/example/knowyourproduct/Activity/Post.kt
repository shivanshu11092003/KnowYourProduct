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
import com.example.knowyourproduct.Model.UserUpload
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.FragmentPostBinding
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


class Post : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference : StorageReference
    private lateinit var imageUri : Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding=FragmentPostBinding.inflate(inflater,container,false)
        binding.selectImage.setOnClickListener{
            val intent = Intent()
            intent.type ="image/*"
            intent.action=Intent.ACTION_PICK
            imageLauncher.launch(intent)



        }
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference =FirebaseDatabase.getInstance().getReference("Users")

        binding.buttontoPost.setOnClickListener{
            val caption = binding.captionid.text.toString()
            val user =  UserUpload(caption)
            if (uid!=null){
                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){
                        uploadpic()


                    }else{

                    }
                }
            }

        }

        return binding.root

    }

    private fun uploadpic() {
        imageUri = Uri.parse("android.resource://${requireContext().packageName}/${R.drawable.unnamed}")
        storageReference =FirebaseStorage.getInstance().getReference("Users"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(requireContext(),"success",Toast.LENGTH_SHORT).show()
            // Clear the caption text
            binding.captionid.setText("")

            // Reset the image view to its initial state
            binding.postPic.setImageDrawable(null)

        }.addOnFailureListener{
            Toast.makeText(requireContext(),"Fail",Toast.LENGTH_SHORT).show()
        }

    }

    val imageLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            if(it.data != null){
                val ref = Firebase.storage.reference.child("Photo"+System.currentTimeMillis()+"."+getfiledata(it.data!!.data))
                ref.putFile(it.data!!.data!!).addOnSuccessListener{
                    ref.downloadUrl.addOnSuccessListener {
                        Firebase.database.reference.child("Photo").push().setValue(it.toString())


                        Picasso.get().load(it.toString()).into(binding.postPic)
                    }
                }
            }
        }
    }

    private fun getfiledata(data: Uri?): String? {
        val r = requireActivity().contentResolver
        val mineType = MimeTypeMap.getSingleton()
        return mineType.getMimeTypeFromExtension(r.getType(data!!))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}