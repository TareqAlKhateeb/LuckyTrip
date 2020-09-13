package com.example.luckytrip.ui.activities.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.luckytrip.R
import com.example.luckytrip.util.setViewVisibility
import kotlinx.android.synthetic.main.activity_host.*


class HostActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navHost: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?

        navController = navHost?.navController

        val navInflater: NavInflater? = navController?.navInflater

        val graph: NavGraph? = navInflater?.inflate(R.navigation.navigation)

        graph?.let { navController?.setGraph(it) }

    }

}