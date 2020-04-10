package com.example.barbershop.retrofit

import com.example.barbershop.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("masters/{id}")
    fun getMaster(@Path("id") id: Int): Call<Master>

    @GET("masters/")
    fun getMasters(): Call<List<Master>>

    @GET("services/{id}")
    fun getService(@Path("id") id: Int): Call<Service>

    @GET("services/")
    fun getServices(): Call<List<Service>>

    @GET("reviews/{id}")
    fun getReview(@Path("id") id: Int): Call<Review>

    @GET("reviews/master/{id}")
    fun getReviewsByMaster(@Path("id") masterId: Int): Call<List<Review>>

    @POST("reviews/")
    fun postReview(@Body review: Review): Call<Review>

    @POST("login/")
    fun login(@Body login: Login): Call<Map<String, String>>

    @POST("register/")
    fun register(@Body register: Register): Call<Map<String, String>>

    @GET("user_sessions/")
    fun getUserSessions(@Body user_name: String): Call<List<Session>>

    @PUT("sessions/{id}/")
    fun putUserSession(
        @Path("id") sessionId: Int,
        @Body session:Session
    ): Call<Session>
}