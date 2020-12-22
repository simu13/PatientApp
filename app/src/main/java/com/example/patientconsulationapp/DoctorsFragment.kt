package com.example.patientconsulationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientconsulationapp.adapter.DoctorAdapter
import com.example.patientconsulationapp.model.Doctor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_doctors.*

class DoctorsFragment : Fragment(R.layout.fragment_doctors) {
    val mfirestore = Firebase.firestore.collection("doctors")
    lateinit var doctorAdapter: DoctorAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        mfirestore.get().addOnSuccessListener {
            for (documents in it)
            {
                val list = documents.toObject(Doctor::class.java)
                val lists:ArrayList<Doctor> = ArrayList()
                lists.add(list)
                doctorAdapter.resetDataSource(lists)

            }
        }
        doctorAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_doctorsFragment_to_appointmentFragment)
        }
    }
    private fun setupRecyclerView() {
        doctorAdapter = activity?.let { DoctorAdapter(it, ArrayList()) }!!
        doctorList.apply {
            adapter = doctorAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}