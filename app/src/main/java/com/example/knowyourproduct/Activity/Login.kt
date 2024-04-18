package com.example.knowyourproduct.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.knowyourproduct.Activity.utils.POST
import com.example.knowyourproduct.Activity.utils.User_Node
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.Model.uploadPost
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await




class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

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
        //already sign in
        if (auth.currentUser != null) {
            // User is already signed in, navigate to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish the login activity to prevent going back to it when pressing back button
        }



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


                            finish()

                            val db = FirebaseFirestore.getInstance()
                            val collectionref = db.collection(User_Node)
                            collectionref.get().addOnSuccessListener {
                                documents ->
                                var emailExists = false

                                for (document in documents) {
                                    val email = document.getString("email")
                                    if (email == Firebase.auth.currentUser!!.email) {
                                        emailExists = true
                                        break
                                    }
                                }
                                       if (!emailExists) {
                                        val d = showUser()

                                        val google =
                                            GoogleDetails(d.accountname, d.profileimage, d.email,d.phoneno)
                                        Firebase.firestore.collection(User_Node).document()
                                            .set(google).addOnSuccessListener {
                                                Toast.makeText(
                                                    this,
                                                    "Welcome",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                    }else{
                                        Toast.makeText(
                                            this,
                                            "Welcome Back",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }








                            }
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


    override fun onStart() {
        super.onStart()
        val currentuser = auth.currentUser
        if (currentuser!=null) run {

            showUser()
        }
  }

    companion object {

        fun showUser(): GoogleDetails {
            val user = Firebase.auth.currentUser
            return if (user != null) {

                val name = user.displayName ?: ""

                val email = user.email ?: ""
                val photourl = user.photoUrl?.toString() ?: ""
                val phoneno = user.phoneNumber?:""

                GoogleDetails(name, photourl,email,phoneno)


            } else {

                GoogleDetails("", "", "","")
            }

        }
    }


}