package com.example.knowyourproduct.Activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerlayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeStatusBarColor("#0074D9") // Replace with your desired color code
        drawerlayout = binding.navDrawer

        setSupportActionBar(binding.toolbar)
        binding.navView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this,drawerlayout,binding.toolbar,
            R.string.open_nav,
            R.string.closed_nav
        )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            replacefragment(Home())
            binding.navView.setCheckedItem(R.id.nav_Home)
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_Home -> replacefragment(Home())
                R.id.nav_Account -> replacefragment(Account())
                R.id.nav_Search -> replacefragment(Search())
                R.id.nav_Post -> replacefragment(Post())
                else ->{
                }
            }
            true



        }



    }

    private fun replacefragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_framelayout,fragment)
        fragmentTransaction.commit()

    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun changeStatusBarColor(color: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = android.graphics.Color.parseColor(color)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_contact -> replacefragment(ContactUs())
            R.id.nav_Account -> replacefragment(Account())
            R.id.nav_Search -> replacefragment(Search())
            R.id.nav_Setting -> replacefragment(Setting())
            R.id.nav_logout -> Toast.makeText(this,"Log_Out",Toast.LENGTH_SHORT).show()

        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }


}