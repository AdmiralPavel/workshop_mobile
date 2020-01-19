package com.example.barbershop.ui.dashboard

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.R
import com.example.barbershop.adapters.BarberAdapter
import com.example.barbershop.models.Barber

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.barber_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val list = mutableListOf<Barber>()
        for (i in 1..9) {
            list.add(
                Barber(
                    "Name $i",
                    "Description, description, description, description, description...$i",
                    (1..5).random(),
                    resources.getDrawable(R.drawable.ic_launcher_background)
                )
            )
        }
        recyclerView.adapter = BarberAdapter(list)
        return root
    }
}