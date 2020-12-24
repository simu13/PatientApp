package com.example.patientconsulationapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.classes.Backend

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(findViewById(R.id.toolbar))

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
                    // Adding the handler to after the a task after some delay.
                    Handler().postDelayed({

                // Here if the user is signed in once and not signed out again from the app. So next time while coming into the app
                // we will redirect him to MainScreen or else to the Intro Screen as it was before.

                // Get the current user id
                val currentUserID = Backend().getCurrentUserID()
                // Start the Intro Activity

                if (currentUserID.isNotEmpty()) {
                    // Start the Main Activity

                    if (currentUserID =="xBkSbml6qNetp0yasr0W4Vurigq1"|| currentUserID =="ODe0XJVlXgRcjkj21K7cmyrRKPR2")
                    {
                        val intent = Intent(this,PatientMainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent(this,DoctorMainActivity::class.java)
                        startActivity(intent)
                    }

                } else {
                    // Start the Intro Activity
                    startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                }
                finish() // Call this when your activity is done and should be closed.
            }, 2500) // Here we pass the delay time in milliSeconds after which the splash activity will disappear.

    }
}