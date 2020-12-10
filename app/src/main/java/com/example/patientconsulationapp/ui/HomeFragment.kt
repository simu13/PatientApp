package com.example.patientconsulationapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.patientconsulationapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.signInButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_loginFragment)
        }
        root.signUpButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_registerFragment)
        }
        if (FirebaseAuth.getInstance().currentUser?.uid != null)
        {
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_mainFragment)
            }
        }
        return root
    }
}