package com.example.barbershop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop.R
import com.example.barbershop.models.Master
import com.example.barbershop.adapters.MasterAdapter
import kotlinx.android.synthetic.main.fragment_barber_recycler.view.*

/**
 * A simple [Fragment] subclass.
 */
class MasterRecyclerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_barber_recycler, container, false)
        root.barbersRecycler.adapter =
            MasterAdapter(arguments!!["masters"] as List<Master>, context!!, fragmentManager!!)
        root.barbersRecycler.layoutManager = LinearLayoutManager(context)
        return root

    }

}


