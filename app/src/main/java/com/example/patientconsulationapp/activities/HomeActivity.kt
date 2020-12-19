package com.example.patientconsulationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.patientconsulationapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
            signInButton.setOnClickListener {
                if (FirebaseAuth.getInstance().currentUser?.uid != null) {
                    view?.let {
                        Navigation.findNavController(it)
                            .navigate(R.id.action_homeFragment_to_mainFragment)
                    }
                } else {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
        signUpButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_registerFragment)
        }

    }
}