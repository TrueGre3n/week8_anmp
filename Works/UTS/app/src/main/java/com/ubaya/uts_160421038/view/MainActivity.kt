package com.ubaya.uts_160421038.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ubaya.uts_160421038.R
import com.ubaya.uts_160421038.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private var username:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getLoginInfo()

        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.bottomNav.setupWithNavController(navController)

    }

    //halo ada manusia disini
    //testing kedua kalinya
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
                || super.onSupportNavigateUp()
    }

    fun getLoginInfo(){
        var loginInfo = "com.zuzudev.yarntopia"
        var shared: SharedPreferences = getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        username = shared.getString("username","").toString()

    }

    override fun onBackPressed() {
        if(username != "")
        {
            Toast.makeText(this, "Please Click Logout in Profile", Toast.LENGTH_SHORT).show()
        }
        else{
            super.onBackPressed()
        }
    }
}