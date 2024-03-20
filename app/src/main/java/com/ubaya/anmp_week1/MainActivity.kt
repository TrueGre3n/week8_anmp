package com.ubaya.anmp_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ubaya.anmp_week1.databinding.ActivityMainBinding
import java.security.AccessController

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup binding di activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,binding.drawerLayout)
        //setupActionBarWithNavController yg diatas. paramenter ke3 drawer klo ada

        //menyuruh agar nav vontroler menghanlde navview(ygsembunyi)
        NavigationUI.setupWithNavController(binding.navView,navController)

        //handle navigation
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        //automaticly pindah ke back stack yang diatasnya
        return NavigationUI.navigateUp(navController,binding.drawerLayout) || super.onSupportNavigateUp()

    }
}