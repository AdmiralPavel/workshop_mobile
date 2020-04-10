package com.example.barbershop.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barbershop.R
import com.example.barbershop.models.Service
import com.example.barbershop.ui.MasterRecyclerFragment

class ServiceAdapter(
    var data: List<Service>,
    private val context: Context,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.element_service, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = data[position]
        holder.name.text = service.name
        holder.price.text = (service.price.toString() + " ₽")
        Glide.with(context).load(service.photo).into(holder.photo)
        holder.description.text = service.description
        holder.itemView.setOnClickListener {
            val fragment = MasterRecyclerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("masters", service.masters)
            fragment.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, fragment, "barberRecyclerFragment")
                .addToBackStack("barberRecyclerFragment").commit()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.service_name)
        val price = view.findViewById<TextView>(R.id.service_price)
        val description = view.findViewById<TextView>(R.id.service_description)
        val photo = view.findViewById<ImageView>(R.id.service_photo)

    }
}