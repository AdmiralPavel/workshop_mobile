package com.example.barbershop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.models.Barber
import de.hdodenhof.circleimageview.CircleImageView

class BarberAdapter(val data: List<Barber>) : RecyclerView.Adapter<BarberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.element_barber, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val barber = data[position]
        holder.name.text = barber.name
        holder.description.text = barber.description
        holder.rating.text = barber.rating.toString()
        holder.photo.setImageDrawable(barber.photo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.barber_name)
        val description = view.findViewById<TextView>(R.id.barber_description)
        val photo = view.findViewById<CircleImageView>(R.id.barber_photo)
        val rating = view.findViewById<TextView>(R.id.barber_rating)
    }
}