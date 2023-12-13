package com.chanuwa.foody.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.chanuwa.foody.R
import com.chanuwa.foody.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupWithNavController(binding.btmNav, navController)
    }
}