package com.ubaya.uts_160421038.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ubaya.uts_160421038.R
import com.ubaya.uts_160421038.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.navHostStart) as NavHostFragment).navController


    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}