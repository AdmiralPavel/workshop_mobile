package com.example.barbershop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barbershop.R
import com.example.barbershop.models.Login
import com.example.barbershop.models.Register
import com.example.barbershop.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)
        root.registrationButton.setOnClickListener {
            root.registrationProgress.visibility = View.VISIBLE

            // Checking if data is correct
            if (registrationEmail.text.toString() == "" || registrationName.text.toString() == "" ||
                registrationPassword.text.toString() == "" || registrationPassword2.text.toString() == ""
            )
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG)
                    .show()
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    registrationEmail.text
                ).matches()
            )
                Toast.makeText(context, "Please enter email correctly", Toast.LENGTH_LONG)
                    .show()
            else if (registrationPassword.text.toString().length < 8)
                Toast.makeText(
                    context,
                    "Your password must be at least 8 symbols length",
                    Toast.LENGTH_LONG
                )
                    .show()
            else if (registrationPassword.text.toString() != registrationPassword2.text.toString())
                Toast.makeText(context, "Your passwords are not the same", Toast.LENGTH_LONG)
                    .show()
            else
                RetrofitClient.register(
                    Register(
                        registrationName.text.toString(),
                        registrationPassword.text.toString(),
                        registrationPassword2.text.toString(),
                        registrationEmail.text.toString()
                    )
                )
                { map ->
                    if (map != null && map["token"] != null) {
                        RetrofitClient.token = "Token " + map["token"]!!
                        RetrofitClient.user_login = Login(
                            root.registrationName.text.toString(),
                            root.registrationPassword.text.toString()
                        )
                        fragmentManager!!.beginTransaction().replace(
                            R.id.main_frame_layout,
                            ProfileFragment(), "Profile"
                        ).commit()
                        Toast.makeText(
                            context,
                            "You have been successfully signed up!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    else if (map!= null && map["error"] == "Exists"){
                        Toast.makeText(
                            context,
                            "User with this name already exists",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    else if (map != null)
                        Toast.makeText(
                            context,
                            "Please check your input data",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    else
                        Toast.makeText(
                            context,
                            "Please check your internet connection",
                            Toast.LENGTH_LONG
                        )
                            .show()
                }
            root.registrationProgress.visibility = View.GONE
        }
        return root
    }


}
