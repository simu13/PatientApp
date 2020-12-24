package com.example.patientconsulationapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.classes.Backend
import com.example.patientconsulationapp.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_final.*
import kotlinx.android.synthetic.main.fragment_final.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FinalFragment : Fragment() {
val args:FinalFragmentArgs by navArgs()
    lateinit var userr: User
    var info:String=""
    val mfirestore = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_final, container, false)
        val doctor = args.doctor
        Toast.makeText(requireContext(),doctor.name + doctor.date + doctor.time,Toast.LENGTH_SHORT).show()
        root.textView.setText(doctor.name)
        root.time.setText(doctor.date)
        root.date.setText(doctor.time)
        Backend().loadUserData(this)
        info = Backend().getCurrentUserID()


        root.confirm.setOnClickListener {
            doctor.name = userr.firstName
            GlobalScope.launch(Dispatchers.IO)
            {
              mfirestore.collection(textView.text.toString()).document(info).set(doctor).await()
            }
        }
        return root
    }
    fun setUserDataInUI(user: User){

        userr = user
    }
}