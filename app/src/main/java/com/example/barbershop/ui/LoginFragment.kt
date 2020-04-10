package com.example.barbershop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barbershop.R
import com.example.barbershop.models.Login
import com.example.barbershop.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_login_screen.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login_screen, container, false)
        root.loginEnter.setOnClickListener {
            root.loginProgress.visibility = View.VISIBLE
            RetrofitClient.login(
                Login(
                    root.loginUsername.text.toString(),
                    root.loginPassword.text.toString()
                )
            )
            { map ->
                if (map != null && map["token"] != null) {
                    RetrofitClient.token = "Token " + map["token"]!!
                    RetrofitClient.user_login = Login(
                        root.loginUsername.text.toString(),
                        root.loginPassword.text.toString()
                    )
                    fragmentManager!!.beginTransaction().replace(
                        R.id.main_frame_layout,
                        ProfileFragment(), "profile"
                    ).commit()
                } else if (map != null)
                    Toast.makeText(context, "Please check your credentials", Toast.LENGTH_LONG)
                        .show()
                else
                    Toast.makeText(
                        context,
                        "Please check your internet connection",
                        Toast.LENGTH_LONG
                    )
                        .show()
                root.loginProgress.visibility = View.GONE
            }
        }
        root.loginSignUp.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(
                R.id.main_frame_layout,
                RegistrationFragment(), "registration"
            ).commit()
        }
        return root
    }

}
