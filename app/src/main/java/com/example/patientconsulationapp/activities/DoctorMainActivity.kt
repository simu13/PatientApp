package com.example.patientconsulationapp.activities

import android.os.Bundle
import android.os.Handler
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.patientconsulationapp.R
import com.google.android.material.navigation.NavigationView


class DoctorMainActivity : AppCompatActivity(){

    private var doubleBackToExitPressedOnce = false
    private lateinit var drawerLayout1: DrawerLayout

    private lateinit var appBarConfiguration1: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)


        drawerLayout1 = findViewById(R.id.drawer_layout1)
        val navView: NavigationView = findViewById(R.id.nav_view1)
        val navController = findNavController(R.id.fragmentNavHost1)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration1 = AppBarConfiguration(setOf(
            R.id.profileFragment2,R.id.mainFragment2
        ), drawerLayout1)
        setupActionBarWithNavController(navController, appBarConfiguration1)
        navView.setupWithNavController(navController)
    }



    override fun onBackPressed() {
        if (findViewById<DrawerLayout>(R.id.drawer_layout1).isDrawerOpen(GravityCompat.START)) {
            findViewById<DrawerLayout>(R.id.drawer_layout1).closeDrawer(GravityCompat.START)
        } else {
            // A double back press function is added in Base Activity.
            doubleBackToExit()
        }
    }


    fun doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            "Please Click Back Again to Exit",
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentNavHost1)
        return navController.navigateUp(appBarConfiguration1) || super.onSupportNavigateUp()
    }

}