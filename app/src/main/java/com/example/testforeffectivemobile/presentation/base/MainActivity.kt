package com.example.testforeffectivemobile.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testforeffectivemobile.R
import com.example.testforeffectivemobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.container_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_main ->
                    if (item.isChecked) return@setOnItemSelectedListener true
                    else navController.navigate(R.id.nav_main)

                R.id.nav_search -> return@setOnItemSelectedListener false

                R.id.nav_cart ->
                    if (item.isChecked) return@setOnItemSelectedListener true
                    else navController.navigate(R.id.nav_cart)

                R.id.nav_account -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }
}