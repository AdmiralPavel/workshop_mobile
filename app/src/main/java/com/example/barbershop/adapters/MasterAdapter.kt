package com.example.barbershop.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barbershop.R
import com.example.barbershop.models.Master
import com.example.barbershop.ui.MasterDetailsFragment
import de.hdodenhof.circleimageview.CircleImageView

class MasterAdapter(
    val data: List<Master>,
    val context: Context,
    val fragmentManager: FragmentManager
) : RecyclerView.Adapter<MasterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.element_master, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val master = data[position]
        holder.itemView.setOnClickListener {
            val fragment = MasterDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("master", master)
            fragment.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, fragment, "barberDetailsFragment")
                .addToBackStack("barberDetailsFragment").commit()
        }
        holder.name.text = master.name
        holder.description.text = master.description
        holder.rating.text = master.rating.toString()
        Glide.with(context).load(master.photo).into(holder.photo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.master_name)
        val description = view.findViewById<TextView>(R.id.service_description)
        val photo = view.findViewById<CircleImageView>(R.id.master_photo)
        val rating = view.findViewById<TextView>(R.id.master_rating)
    }

}