package com.example.barbershop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.barbershop.R
import com.example.barbershop.adapters.SessionAdapter
import com.example.barbershop.models.Master
import kotlinx.android.synthetic.main.fragment_master_details.view.*


class MasterDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_master_details, container, false)
        val master = arguments?.get("master") as Master
        val data = master.sessions
        root.sessionRecyclerView.adapter =
            SessionAdapter(data, context!!, master.id, SessionAdapter.ADAPTER_TYPE_MASTER)
        root.sessionRecyclerView.layoutManager = GridLayoutManager(context, 3)
        root.master_details_name.text = master.name
        Glide.with(context!!).load(master.photo).into(root.masterDetailsPhoto)

        root.reviewsButton.setOnClickListener {
            val fragment = ReviewRecyclerFragment()
            val bundle = Bundle()
            bundle.putParcelable("master", master)
            fragment.arguments = bundle
            fragmentManager!!.beginTransaction()
                .replace(R.id.main_frame_layout, fragment, "reviewRecyclerFragment")
                .addToBackStack("reviewRecyclerFragment").commit()
        }
        return root
    }

}
