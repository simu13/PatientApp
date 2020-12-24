package com.example.patientconsulationapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.patientconsulationapp.R
import kotlinx.android.synthetic.main.partial_time.view.*
import java.util.*
import kotlin.collections.ArrayList

class TimeAdapter(
    private val context: Context,
    private var list: ArrayList<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var onItemClickListener: ((String) -> Unit)? = null
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
        holder.itemView.buttonTime.text = item
        holder.itemView.setOnClickListener {
//onItemClickListener?.let { it(item) }
            if (onItemClickListener == null){
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    fun resetDataSource(weather: ArrayList<String>) {
        list = weather
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}