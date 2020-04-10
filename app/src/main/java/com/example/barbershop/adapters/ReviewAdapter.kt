package com.example.barbershop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barbershop.R
import com.example.barbershop.models.Review

class ReviewAdapter(val data: List<Review>, val context: Context) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.element_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = data[position]
        holder.name.text = review.user_name
        holder.rating.text = review.rating.toString()
        holder.date.text = review.date.toString()
        holder.photo.setImageResource(R.drawable.ic_person)
        holder.text.text = review.text
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.review_user_name)
        val rating = view.findViewById<TextView>(R.id.review_rating)
        val date = view.findViewById<TextView>(R.id.review_date)
        val text = view.findViewById<TextView>(R.id.review_text)
        val photo = view.findViewById<ImageView>(R.id.review_user_photo)
    }
}