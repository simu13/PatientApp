package com.example.patientconsulationapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.patientconsulationapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class DoctorLoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        root.login.setOnClickListener {
                loginUser()
        }
        return root
    }
    private fun loginUser() {
        //Inserting the username in variable
        val email = email.text.toString()
        //Inserting the password in variable
        val password = password.text.toString()
        //Sending the arguments for authentication
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        val user = auth.currentUser
                        if (user!!.uid =="xBkSbml6qNetp0yasr0W4Vurigq1"||user!!.uid =="ODe0XJVlXgRcjkj21K7cmyrRKPR2") {
                            view?.let {
                                Navigation.findNavController(it)
                                    .navigate(R.id.action_doctorLoginFragment_to_profileFragment)
                            }
                        }
                        else {
                            auth.signOut()
                            Toast.makeText(activity,"Invalid Details",Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

