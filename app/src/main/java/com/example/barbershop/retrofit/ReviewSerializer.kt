package com.example.barbershop.retrofit

import com.example.barbershop.models.Review
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.SimpleDateFormat


class ReviewSerializer : JsonSerializer<Review> {
    override fun serialize(
        review: Review,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val obj = JsonObject()
        obj.addProperty("user_name", review.user_name)
        obj.addProperty("text", review.text)
        obj.addProperty("rating", review.rating)
        obj.addProperty("master", review.master)
        obj.addProperty("date", SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(review.date))
        return obj
    }
}