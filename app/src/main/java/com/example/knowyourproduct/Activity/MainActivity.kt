package com.example.knowyourproduct.Activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.knowyourproduct.Model.GoogleDetails
import com.example.knowyourproduct.R
import com.example.knowyourproduct.databinding.ActivityMainBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.squareup.picasso.Picasso



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
                R.id.nav_Setting ->replacefragment(Setting())
                else ->{
                }
            }
            true



        }
        val googleData=Login.showUser()
        processData(googleData)



    }
    private fun processData(googleData: GoogleDetails) {
        if (googleData != null) {
            val headerview = binding.navView.getHeaderView(0)
            val headerpic = headerview?.findViewById<ShapeableImageView>(R.id.imageID)
            val headeraccountname = headerview?.findViewById<TextView>(R.id.accountnameid)
            val headeremail = headerview?.findViewById<TextView>(R.id.email_navholder)

            // Access and use the properties of the UserData object
            headeraccountname?.text = googleData.accountname
            Picasso.get().load(googleData.profileimage).into(headerpic)
            headeremail?.text = googleData.email
        }else{
            val headerview = binding.navView.getHeaderView(0)
            val headerpic = headerview.findViewById<ShapeableImageView>(R.id.imageID)
            val headeraccountname = headerview.findViewById<TextView>(R.id.accountnameid)
            val headeremail = headerview.findViewById<TextView>(R.id.email_navholder)
            headeraccountname.text =" googleData.accountname"
            Picasso.get().load(R.drawable.unnamed).into(headerpic)
            headeremail.text = "googleData.email"

        }





        // Use the data as needed

    }

    private fun replacefragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_framelayout,fragment)
        fragmentTransaction.commit()

    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun changeStatusBarColor(color: String) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = android.graphics.Color.parseColor(color)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_contact -> replacefragment(ContactUs())
            R.id.nav_Account -> replacefragment(Account())
            R.id.nav_Search -> replacefragment(Search())
            R.id.nav_Setting -> replacefragment(Setting())
            R.id.nav_logout -> {
                Firebase.auth.signOut()
                val headerview = binding.navView.getHeaderView(0)
                val headerpic=headerview.findViewById<ShapeableImageView>(R.id.imageID)
                val headeraccountname = headerview.findViewById<TextView>(R.id.accountnameid)
                val headeremail =headerview.findViewById<TextView>(R.id.email_navholder)
                headeraccountname.text="Please Login "
                headerpic.setImageResource(R.drawable.unnamed)
                headeremail.text="--"
            }
            R.id.nav_login -> {
                finish()
                startActivity(Intent(applicationContext,Login::class.java))
            }


        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.chat -> {
                replacefragment(Chat())


                true
            }
            R.id.search ->{
                replacefragment(Search())
                true
            }
            // Add more cases for other menu items
            else -> super.onOptionsItemSelected(item)
        }
    }




}



