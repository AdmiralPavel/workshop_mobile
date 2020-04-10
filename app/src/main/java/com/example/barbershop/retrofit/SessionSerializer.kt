package com.example.barbershop.retrofit

import com.example.barbershop.models.Session
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.SimpleDateFormat

class SessionSerializer : JsonSerializer<Session> {
    override fun serialize(
        session: Session,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val obj = JsonObject()
        obj.addProperty("user_name", session.user_name)
        obj.addProperty("master", session.master)
        obj.addProperty("time", SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(session.time))
        return obj
    }
}