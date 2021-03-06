package com.example.patientconsulationapp.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.adapter.DoctorAdapter
import com.example.patientconsulationapp.model.Doctor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_doctors.*

class DoctorsFragment : Fragment(R.layout.fragment_doctors) {
    val mfirestore = Firebase.firestore.collection("doctors")
    lateinit var doctorAdapter: DoctorAdapter

                    val lists:ArrayList<Doctor> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lists.clear()
        mfirestore.get().addOnSuccessListener {
            for (documents in it)
            {
                val list = documents.toObject(Doctor::class.java)
//                val lists:ArrayList<Doctor> = ArrayList()
                lists.add(list)
            }

            doctorAdapter.resetDataSource(lists)


        }
        doctorAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("doctor", it)
            }
            findNavController().navigate(R.id.action_doctorsFragment_to_appointmentFragment,bundle)
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