package com.example.patientconsulationapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.classes.Backend
import com.example.patientconsulationapp.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment(), View.OnClickListener {

    private val cal = Calendar.getInstance()
    private lateinit var auth: FirebaseAuth
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        dateSetListener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            cal.set(Calendar.YEAR, i)
            cal.set(Calendar.MONTH, i2)
            cal.set(Calendar.DAY_OF_MONTH, i3)

            updateDateInView()
        }
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_register, container, false)

       root.et_date.setOnClickListener(this)

        root.signUp.setOnClickListener {
            registerUser()
        }
        root.btn_tv_login.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return root
    }

    private fun registerUser() {
        val email = name.text.toString()
        val password = password.text.toString()
        Log.i("Firebase", email)
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val date = et_date.text.toString()
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task->
                        if (task.isSuccessful )
                        {
                            val user = User("",email,"",date)
                            Backend().registerUser(user)
                        }
                    }.await()
                    withContext(Dispatchers.Main) {
                        val user = auth.currentUser
                        user!!.sendEmailVerification()
                        view?.let {
                            //Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        else{
            Toast.makeText(activity, "DONE", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.et_date -> {
                activity?.let {
                    DatePickerDialog(
                        it,
                        dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }
        }
    }
    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        et_date.setText(sdf.format(cal.time).toString())
    }
}