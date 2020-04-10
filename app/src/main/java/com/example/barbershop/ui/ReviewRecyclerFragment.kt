package com.example.barbershop.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapters.ReviewAdapter
import com.example.barbershop.models.Master
import com.example.barbershop.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_review_recycler.view.*


/**
 * A simple [Fragment] subclass.
 */
class ReviewRecyclerFragment : Fragment() {

    lateinit var master: Master
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_review_recycler, container, false)
        master = arguments!!["master"]!! as Master
        root.reviewRecyclerView.adapter =
            ReviewAdapter(master.reviews, context!!)
        root.reviewRecyclerView.layoutManager = LinearLayoutManager(context)
        setHasOptionsMenu(true)
        return root
    }

    private fun getReviews(root: View, masterId: Int) {
        RetrofitClient.getReviews(masterId) { reviewsList ->
            if (reviewsList != null) {
                root.reviewRecyclerView.adapter =
                    ReviewAdapter(reviewsList, context!!)
            } else {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.reviews_recycler_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = ReviewCreationDialog()
        val bundle = Bundle()
        bundle.putInt("master_id", master.id)
        fragment.arguments = bundle
        fragment.show(fragmentManager!!, "reviewCreationDialog")
        return super.onOptionsItemSelected(item)
    }
}
