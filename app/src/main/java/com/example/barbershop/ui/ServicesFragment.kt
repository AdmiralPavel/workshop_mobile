package com.example.barbershop.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapters.ServiceAdapter
import com.example.barbershop.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_services.view.*

class ServicesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_services, container, false)
        root.swipeRefresh.setOnRefreshListener { getServices(root) }
        root.servicesRecycler.layoutManager = LinearLayoutManager(context)
        getServices(root)
        return root
    }

    private fun getServices(root: View) {
        RetrofitClient.getServices { servicesList ->
            if (servicesList != null) {
                root.servicesRecycler.adapter =
                    ServiceAdapter(servicesList, context!!, fragmentManager!!)
            } else {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_LONG)
                    .show()
            }
        }
        root.swipeRefresh.isRefreshing = false
    }
}