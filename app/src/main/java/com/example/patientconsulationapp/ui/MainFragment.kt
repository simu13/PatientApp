package com.example.patientconsulationapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.adapter.PatientAdapter
import com.example.patientconsulationapp.model.Patient
import com.example.patientconsulationapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


class MainFragment : Fragment(R.layout.fragment_main) {
    val mfirestore = Firebase.firestore
    lateinit var patientAdapter: PatientAdapter
    //var userr: User = User()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        // Inflate the layout for this fragment

        setupRecyclerView()
        setUserData()
        patientAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("patient", it)
            }
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_mainFragment2_to_patientDetailsFragment,bundle) }
                /*val i = Intent()
                i.setPackage("com.google.android.apps.tachyon")
                i.action = "com.google.android.apps.tachyon.action.CALL"
                i.data = Uri.parse("tel:7006628764")
                startActivity(i)*/

        }
        //Backend().loadUserData(this)




    }
    fun setUserData(){


            val info = Firebase.firestore.collection("User")
                .document((FirebaseAuth.getInstance().currentUser?.uid.toString()))
                .get().addOnSuccessListener {
                    if (it!=null)
                    {
                        val doc = it.toObject(User::class.java)
                        if (doc != null) {
                            val userr =doc
                            loadData(userr)

                        }
                    }
                }



    }
    private fun setupRecyclerView() {
        patientAdapter = activity?.let { PatientAdapter(it, ArrayList()) }!!
        doctorList.apply {
            adapter = patientAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    fun loadData(user: User){
        mfirestore.collection("Doctor: " +user.firstName).get().addOnSuccessListener {
            for (documents in it)
            {
                val list = documents.toObject(Patient::class.java)
                val lists:ArrayList<Patient> = ArrayList()
                lists.add(list)
                patientAdapter.resetDataSource(lists)

            }

        }
    }
}

