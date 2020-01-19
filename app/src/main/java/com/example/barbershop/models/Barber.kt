package com.example.barbershop.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

data class Barber(
    val name: String,
    val description: String,
    val rating: Int,
    val photo: Drawable
)