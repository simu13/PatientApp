package com.example.patientconsulationapp.patient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.adapter.Times2Adapter
import com.example.patientconsulationapp.model.Times

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_time2.*

class TimeFragment2 : Fragment(R.layout.fragment_time2) {
    private val mfirestore = Firebase.firestore.collection("appointments")
    lateinit var time2Adapter: Times2Adapter
    val args: TimeFragment2Args by navArgs()
    val lists:ArrayList<Times> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doctor = args.doctor
        lists.clear()
        setupRecyclerView()

        mfirestore.document("22.12.2020").collection("time").get().addOnSuccessListener {
            for (documents in it)
            {
                val list = documents.toObject(Times::class.java)

                lists.add(list)
                Log.i("Time2", lists.toString())


            }
            time2Adapter.resetDataSource(lists)
        }
        time2Adapter.setOnItemClickListener {
doctor.time = it.time
            val bundle = Bundle().apply {
                putSerializable("doctors", doctor)
            }
            findNavController().navigate(R.id.action_timeFragment2_to_finalFragment,bundle)
        }
    }
    private fun setupRecyclerView() {
        time2Adapter = activity?.let { it1 -> Times2Adapter(it1, ArrayList()) }!!
        rv2.apply {
            adapter = time2Adapter
            layoutManager = GridLayoutManager(activity, 3)
        }
    }
}