package com.example.patientconsulationapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        GlobalScope.launch(Dispatchers.IO) {
            val info = Firebase.firestore.collection("User")
                .document((FirebaseAuth.getInstance().currentUser?.uid.toString()))
                .get().await().toObject(User::class.java)
            withContext(Dispatchers.Main){
                if (info != null) {
                    et_name.setText(info.firstName)
                    et_email.setText(info.email)
                    et_mobile.setText(info.dateOfBirth)
                }
            }
        }
        return root
    }

}