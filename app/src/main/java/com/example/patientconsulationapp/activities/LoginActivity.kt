package com.example.patientconsulationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            auth = FirebaseAuth.getInstance()
           login.setOnClickListener {
                loginUser()
            }
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
                            if (user!!.isEmailVerified) {
                                view?.let {
                                    Navigation.findNavController(this)
                                        .navigate(R.id.action_loginFragment_to_mainFragment)
                                }
                            }
                            else {
                                Toast.makeText(
                                    applicationContext,
                                    "Please Verify Your Email Address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }