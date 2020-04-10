package com.example.barbershop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.models.Session
import com.example.barbershop.retrofit.RetrofitClient
import java.text.SimpleDateFormat

class SessionAdapter(
    val data: ArrayList<Session>,
    val context: Context,
    val master_id: Int,
    val adapterType: Int

) :

    RecyclerView.Adapter<SessionAdapter.ViewHolder>() {
    companion object {
        val ADAPTER_TYPE_MASTER = 0
        val ADAPTER_TYPE_PROFILE = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.element_session, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = data[position]
        val pattern = "dd.MM\nHH:mm"
        val format = SimpleDateFormat(pattern)
        holder.sessionView.text = format.format(session.time)
        holder.itemView.setOnClickListener {
            RetrofitClient.putUserSession(session, master_id) {
                if (it != null) {
                    data.removeAt(position)
                    Toast.makeText(
                        context,
                        "You successfully signed up at ${holder.sessionView.text}",
                        Toast.LENGTH_LONG
                    ).show()
                    notifyDataSetChanged()
                } else
                    Toast.makeText(
                        context,
                        "Please check your connection to internet",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sessionView = view.findViewById<TextView>(R.id.sessionView)
    }
}