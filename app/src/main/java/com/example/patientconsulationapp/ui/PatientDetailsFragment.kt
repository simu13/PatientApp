package com.example.patientconsulationapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.patientconsulationapp.R
import kotlinx.android.synthetic.main.fragment_final.view.*
import kotlinx.android.synthetic.main.fragment_patient_details.view.*
import kotlinx.android.synthetic.main.fragment_patient_details.view.date
import kotlinx.android.synthetic.main.fragment_patient_details.view.textView
import kotlinx.android.synthetic.main.fragment_patient_details.view.time

class PatientDetailsFragment : Fragment() {
    val args: PatientDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_patient_details, container, false)
        val patient = args.patient
        root.textView.setText("Doctor: " +patient.name)
        root.time.setText("Time: " + patient.date)
        root.date.setText("Date: " + patient.time)
        //val number = patient.phn
        //val uri =
        root.videoCall.setOnClickListener {
            val i = Intent()
            i.setPackage("com.google.android.apps.tachyon")
            i.action = "com.google.android.apps.tachyon.action.CALL"
            i.data = Uri.parse("tel:${patient.phn}")
            startActivity(i)
        }
        return root
    }
}