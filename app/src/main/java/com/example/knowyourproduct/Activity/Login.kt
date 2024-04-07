package com.example.knowyourproduct.Activity

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Identity
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.trusted.Token
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googlesigninclient : GoogleSignInClient
    private  var oneTapClient:SignInClient?=null
    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(getString(R.string.default_web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
            .build()


    }
    private suspend fun signInGoogle(){
        val result = oneTapClient?.beginSignIn(signInRequest)?.await()
        val intentSenderRequest = IntentSenderRequest.Builder(result!!.pendingIntent).build()
        activityResultLauncher.launch(intentSenderRequest)
    }
    private val activityResultLauncher : ActivityResultLauncher<IntentSenderRequest> = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ){
        result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credential = oneTapClient!!.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                if (idToken != null) {
                    val firebasecredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebasecredential).addOnCompleteListener {
                        if (it.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                            Toast.makeText(this, "Sign In Complete", Toast.LENGTH_SHORT).show()
                        }


                    }
                }

            }catch (e : ApiException){
                e.printStackTrace()
            }
        }
    }
    fun signwithgoogle(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            signInGoogle()
        }



    }
    fun signoutgoogle(view: View) {
        Firebase.auth.signOut()
        Toast.makeText(this,"Sign out Complete",Toast.LENGTH_SHORT).show()


    }
//    private fun showUser(){
//        val user = Firebase.auth.currentUser
//        user?.let{
//            val name = it.displayName
//            val email = it.email
//            val photourl = it.photoUrl
//            val emailverifies = it.isEmailVerified
//            val userdata = GoogleDetails(name.toString(),photourl.toString(),email.toString(),emailverifies.toString())
//
//
//        }
//    }






}