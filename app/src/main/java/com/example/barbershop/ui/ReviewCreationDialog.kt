package com.example.barbershop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.barbershop.R
import com.example.barbershop.models.Review
import com.example.barbershop.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_review_creation_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ReviewCreationDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_review_creation_dialog, container, false)
        val arrayList = ArrayList<Int>()
        for (i in 1..10)
            arrayList.add(i)
        root.reviewDialogSpinner.adapter =
            ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, arrayList)
        root.reviewDialogConfirm.setOnClickListener {
            RetrofitClient.postReview(
                Review(
                    RetrofitClient.user_login.username,
                    root.reviewDialogEditText.text.toString(),
                    root.reviewDialogSpinner.selectedItem.toString().toInt(),
                    Calendar.getInstance().time,
                    arguments!!["master_id"]!! as Int
                )
            ) {
                Toast.makeText(
                    context,
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().time),
                    Toast.LENGTH_LONG
                ).show()
                if (it == null)
                    Toast.makeText(
                        context,
                        "Please check your internet connection",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    Toast.makeText(
                        context,
                        "Your review has been successfully added",
                        Toast.LENGTH_LONG
                    ).show()
                    dismiss()
                }

            }
        }
        return root
    }

}
