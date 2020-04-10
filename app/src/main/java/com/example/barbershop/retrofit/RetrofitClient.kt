package com.example.barbershop.retrofit

import com.example.barbershop.models.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    var user_login: Login = Login("", "")
    private var retrofitService: RetrofitService
    var token: String = "Token 42ec5e467114c3e2a6f232c10d053f48b36cfe53"


    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder().addHeader(
                "Authorization",
                token
            ).build()
            chain.proceed(request)
        }
        val builder = GsonBuilder()
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        builder.registerTypeAdapter(Review::class.java, ReviewSerializer())
        builder.registerTypeAdapter(Session::class.java, SessionSerializer())
        val gson = builder.create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.130:8000/api/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


        retrofitService = retrofit.create(RetrofitService::class.java)
    }


    fun getService(id: Int, onDataReceived: (Service?) -> Unit) {
        val getMyServiceCallback = object : Callback<Service> {
            override fun onFailure(call: Call<Service>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(
                call: Call<Service>,
                response: Response<Service>
            ) {
                onDataReceived(response.body())
            }
        }
        val requestList: Call<Service> = retrofitService.getService(id)
        requestList.enqueue(getMyServiceCallback)
    }

    fun getServices(onDataReceived: (List<Service>?) -> Unit) {
        val getMyServicesCallback = object : Callback<List<Service>> {
            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                onDataReceived(response.body())
            }

        }
        val requestList = retrofitService.getServices()
        requestList.enqueue(getMyServicesCallback)
    }


    fun getMaster(id: Int, onDataReceived: (Master?) -> Unit) {
        val getMyMasterCallback = object : Callback<Master> {
            override fun onFailure(call: Call<Master>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(
                call: Call<Master>,
                response: Response<Master>
            ) {
                onDataReceived(response.body())
            }
        }
        val requestList: Call<Master> = retrofitService.getMaster(id)
        requestList.enqueue(getMyMasterCallback)
    }

    fun getMasters(onDataReceived: (List<Master>?) -> Unit) {
        val getMyMastersCallback = object : Callback<List<Master>> {
            override fun onFailure(call: Call<List<Master>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<List<Master>>, response: Response<List<Master>>) {
                onDataReceived(response.body())
            }

        }
        val requestList = retrofitService.getMasters()
        requestList.enqueue(getMyMastersCallback)
    }


    fun getReview(id: Int, onDataReceived: (Review?) -> Unit) {
        val getMyMasterCallback = object : Callback<Review> {
            override fun onFailure(call: Call<Review>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(
                call: Call<Review>,
                response: Response<Review>
            ) {
                onDataReceived(response.body())
            }
        }
        val requestList: Call<Review> = retrofitService.getReview(id)
        requestList.enqueue(getMyMasterCallback)
    }

    fun postReview(review: Review, onDataReceived: (Review?) -> Unit) {
        val postReviewCallback = object : Callback<Review> {
            override fun onFailure(call: Call<Review>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<Review>, response: Response<Review>) {
                onDataReceived(response.body())
            }

        }
        val request = retrofitService.postReview(review)
        request.enqueue(postReviewCallback)
    }

    fun getReviews(masterId: Int, onDataReceived: (List<Review>?) -> Unit) {
        val getReviewsCallback = object : Callback<List<Review>> {
            override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<List<Review>>, response: Response<List<Review>>) {
                onDataReceived(response.body())
            }

        }
        val request = retrofitService.getReviewsByMaster(masterId)
        request.enqueue(getReviewsCallback)
    }

    fun login(login: Login, onDataReceived: (Map<String, String>?) -> Unit) {
        val loginCallback = object : Callback<Map<String, String>> {
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                onDataReceived(response.body())
            }
        }
        val request = retrofitService.login(login)
        request.enqueue(loginCallback)
    }

    fun register(register: Register, onDataReceived: (Map<String, String>?) -> Unit) {
        val registerCallback = object : Callback<Map<String, String>> {
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                onDataReceived(response.body())
            }
        }
        val request = retrofitService.register(register)
        request.enqueue(registerCallback)
    }

    fun getUserSessions(user_name: String, onDataReceived: (List<Session>?) -> Unit) {
        val callback = object : Callback<List<Session>> {
            override fun onFailure(call: Call<List<Session>>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<List<Session>>, response: Response<List<Session>>) {
                onDataReceived(response.body())
            }

        }
        val request = retrofitService.getUserSessions(user_name)
        request.enqueue(callback)
    }

    fun putUserSession(session: Session, masterId: Int, onDataReceived: (Session?) -> Unit) {
        val callback = object : Callback<Session> {
            override fun onFailure(call: Call<Session>, t: Throwable) {
                onDataReceived(null)
            }

            override fun onResponse(call: Call<Session>, response: Response<Session>) {
                onDataReceived(response.body())
            }
        }
        val request = retrofitService.putUserSession(
            session.id,
            session
        )
        request.enqueue(callback)
    }
}