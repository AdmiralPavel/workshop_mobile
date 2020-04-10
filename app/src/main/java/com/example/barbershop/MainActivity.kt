package com.example.barbershop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.ui.HomeFragment
import com.example.barbershop.ui.LoginFragment
import com.example.barbershop.ui.ServicesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginFragment = LoginFragment()
        val homeFragment = HomeFragment()
        val servicesFragment = ServicesFragment()
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.main_frame_layout,
                        homeFragment, "home"
                    ).addToBackStack("home").commit()
                    true
                }
                R.id.navigation_services -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.main_frame_layout,
                        servicesFragment, "services"
                    ).addToBackStack("services").commit()
                    true
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.main_frame_layout,
                        loginFragment, "login"
                    ).addToBackStack("login").commit()
                    true
                }
                else -> false
            }

        }
            supportFragmentManager.beginTransaction().replace(
                R.id.main_frame_layout,
                homeFragment, "home"
            ).addToBackStack("home").commit()

    }

    override fun onResume() {
        bottomNavigation.visibility = View.VISIBLE
        super.onResume()
    }
}
