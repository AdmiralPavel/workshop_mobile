package com.example.barbershop.models

class Service(
    val id: Int,
    val name: String,
    val description: String,
    val photo: String,
    val price: Int,
    val masters: ArrayList<Master>

)