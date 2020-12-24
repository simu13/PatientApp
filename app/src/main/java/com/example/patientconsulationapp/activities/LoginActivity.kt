package com.example.patientconsulationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.patientconsulationapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

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
           login.setOnClickListener {view ->
                loginUser(view)
            }
        }
        private fun loginUser(view:View) {
            //Inserting the username in variable
            val email = name.text.toString()
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
                                auth.signOut()
                                Toast.makeText(this@LoginActivity,"Invalid Details",Toast.LENGTH_SHORT).show()
                            }
                            else {

                                   startActivity(Intent(this@LoginActivity,PatientMainActivity::class.java))

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