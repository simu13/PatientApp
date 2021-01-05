package com.example.patientconsulationapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.patientconsulationapp.R
import com.example.patientconsulationapp.model.Times
import kotlinx.android.synthetic.main.partial_doctor.view.*
import kotlinx.android.synthetic.main.partial_time.view.*
import java.util.*
import kotlin.collections.ArrayList

class TimesAdapter(
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
        //holder.itemView.call.setOnClickListener {  }
        holder.itemView.setOnClickListener {
//onItemClickListener?.let { it(item) }
            onItemClickListener?.let { it(item) }
        }

    }
    fun setOnItemClickListener(listener: (Times) -> Unit) {
        onItemClickListener = listener
    }

    fun resetDataSource(weather: ArrayList<Times>) {
        list = weather
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}