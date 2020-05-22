package com.example.midterm01mariamichakhvadze

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

object DataLoader {
    private var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://rickandmortyapi.com/api/")
        .build()

    private var service = retrofit.create(ApiRetrofit::class.java)

    fun getRequest(url: String, customCallback: CustomCallback) {
        val call = service.getRequest(url)
        call.enqueue(callback(customCallback))
    }

    private fun callback(customCallback: CustomCallback) = object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            customCallback.onFailed(t.message.toString())
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            customCallback.onSuccess(response.body().toString())
        }
    }
}

interface ApiRetrofit {
    @GET
    fun getRequest(@Url url: String): Call<String>
}