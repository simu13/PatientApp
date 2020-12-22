package com.example.patientconsulationapp

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.patientconsulationapp.adapter.TimeAdapter
import com.example.patientconsulationapp.classes.Backend
import com.example.patientconsulationapp.model.Booking
import com.example.patientconsulationapp.model.Time
import com.example.patientconsulationapp.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_appointment.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AppointmentFragment : Fragment(), View.OnClickListener {
    // Create a instance of Firebase Firestore
    private val mFireStore = Firebase.firestore.collection("appointments")
    var time: String = ""
    private val cal = Calendar.getInstance()
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeAdapter: TimeAdapter
    lateinit var mUserDetails:User
    var name = ""
    var email = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_appointment, container, false)
        root.et_date.setOnClickListener(this)
        dateSetListener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            cal.set(Calendar.YEAR, i)
            cal.set(Calendar.MONTH, i2)
            cal.set(Calendar.DAY_OF_MONTH, i3)
            timeAdapter = activity?.let { it1 -> TimeAdapter(it1, ArrayList<String>()) }!!
            root.rv.adapter = timeAdapter
            root.rv.layoutManager = GridLayoutManager(activity, 3)
            updateDateInView()

        }

        root.confirm.setOnClickListener {
            val data = et_date.text.toString()
            //val time = et_date1.text.toString()
            val booking = Booking(name, data, time, email)
            Toast.makeText(activity, "${booking.time}", Toast.LENGTH_LONG).show()
        }


        return root
    }
    fun setUserDataInUI(user: User) {

        // Initialize the user details variable
        mUserDetails = user

        //Glide.with(this@AppointmentFragment).load(user.image).centerCrop().into(iv_profile_user_image)

        et_name.setText(user.firstName)
        name = user.firstName
        email = user.email

    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        et_date.setText(sdf.format(cal.time).toString())
        Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show()
        GlobalScope.launch(Dispatchers.IO) {
            mFireStore.document(et_date.text.toString()).get()
                .addOnSuccessListener {
                    if (it != null) {
                        val list = it.toObject(Time::class.java)
                        timeAdapter.resetDataSource(list!!.time)
                        Toast.makeText(activity, "${list.time}", Toast.LENGTH_LONG).show()

                    }
                }
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

}