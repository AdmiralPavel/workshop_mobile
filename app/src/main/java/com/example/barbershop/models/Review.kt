package com.example.barbershop.models

import android.graphics.drawable.Drawable
import java.util.*

data class Review(
    val name: String,
    val text: String,
    val rating: Int,
    val photo: Drawable,
    val date: Date
)