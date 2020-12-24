package com.example.patientconsulationapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientconsulationapp.AppointmentFragmentArgs
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.adapter.TimeAdapter
import com.example.patientconsulationapp.model.Time
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_time.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TimeFragment : Fragment(R.layout.fragment_time)
{
    private val mFireStore = Firebase.firestore.collection("appointments")
    lateinit var timeAdapter: TimeAdapter
    val args: TimeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doctor = args.doctor
        setupRecyclerView()
        timeAdapter.setOnItemClickListener {
          et_time.setText(it)
            doctor.time = et_time.text.toString()
            val bundle = Bundle().apply {
                putSerializable("doctor", doctor)
            }
            Navigation.findNavController(view).navigate(R.id.action_timeFragment_to_finalFragment,bundle)
        }
        confirm.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("doctor", doctor)
            }
            Navigation.findNavController(it).navigate(R.id.action_timeFragment_to_finalFragment,bundle)
            }
        GlobalScope.launch(Dispatchers.IO) {
            mFireStore.document(doctor.date).get()
                .addOnSuccessListener {
                    if (it != null) {
                        val list = it.toObject(Time::class.java)
                        timeAdapter.resetDataSource(list!!.time)
                        Toast.makeText(activity, "${list.time}", Toast.LENGTH_LONG).show()

                    }
                }
        }
    }
    private fun setupRecyclerView() {
        timeAdapter = activity?.let { it1 -> TimeAdapter(it1, ArrayList<String>()) }!!
        rv1.apply {
            adapter = timeAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
    }
    }
