package com.example.patientconsulationapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.model.Doctor
import com.example.patientconsulationapp.model.Times
import kotlinx.android.synthetic.main.partial_doctor.view.*
import kotlinx.android.synthetic.main.partial_time.view.*
import java.util.*
import kotlin.collections.ArrayList

class Times2Adapter(
    private val context: Context,
    private var list: ArrayList<Times>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var onItemClickListener: ((Times) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.partial_time,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.buttonTime.text = item.time
        holder.itemView.available.text = item.booked
        holder.itemView.setOnClickListener {
        onItemClickListener?.let { it(item) }
        }

    }
    fun setOnItemClickListener(listener: (Times) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun resetDataSource(weather: ArrayList<Times>) {
        list = weather
        notifyDataSetChanged()
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}