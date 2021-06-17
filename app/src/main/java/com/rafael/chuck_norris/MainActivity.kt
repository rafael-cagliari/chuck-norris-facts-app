package com.rafael.chuck_norris

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rafael.chuck_norris.databinding.ActivityMainBinding
import com.rafael.domain.model.ChuckNorrisFact

class MainActivity() : AppCompatActivity() {
    @SuppressLint("ResourceType")
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set main activity layout as nav host fragment
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navigation_fragment) as NavHostFragment
        navController = navHostFragment.navController


        val mockChuckNorrisFact: ChuckNorrisFact = ChuckNorrisFact(
            id = "469879812sdasdasd",
            categories = listOf("dev"),
            value = "chuck norris is da best"
        )

    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}


