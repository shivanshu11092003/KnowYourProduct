package com.example.knowyourproduct.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.knowyourproduct.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            if (isLoggenIn()){
             navigatetoHome() }
            else {
             navigatetoLogin() }
            finish()
                              } , 3000)
    }

    private fun isLoggenIn(): Boolean {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)

    }


    private fun navigatetoLogin() {

    }

    private fun navigatetoHome() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)    }
}