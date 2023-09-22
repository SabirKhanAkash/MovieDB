package com.akash.moviedb.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.akash.moviedb.R
import com.akash.moviedb.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.mainContainer)

        NavigationUI.setupWithNavController(navigationView, navController)
    }
}