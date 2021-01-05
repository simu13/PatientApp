package com.example.patientconsulationapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.model.Doctor
import kotlinx.android.synthetic.main.partial_doctor.view.*
import kotlinx.android.synthetic.main.partial_time.view.*
import java.util.*
import kotlin.collections.ArrayList

class DoctorAdapter(
    private val context: Context,
    private var list: ArrayList<Doctor>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var onItemClickListener: ((Doctor) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.partial_doctor,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.textViewTime.text = item.name
        holder.itemView.setOnClickListener {
onItemClickListener?.let { it(item) }
        }

    }
    fun setOnItemClickListener(listener: (Doctor) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun resetDataSource(weather: ArrayList<Doctor>) {
        list = weather
        notifyDataSetChanged()
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}