package com.example.barbershop.models

import java.util.*

data class Review(
    val user_name: String,
    val text: String,
    val rating: Int,
    val date: Date,
    val master: Int
)