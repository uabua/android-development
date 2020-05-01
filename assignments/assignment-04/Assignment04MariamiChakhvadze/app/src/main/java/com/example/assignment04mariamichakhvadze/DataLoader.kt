package com.example.assignment04mariamichakhvadze

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object DataLoader {
    private var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://reqres.in/api/")
        .build()

    private var service = retrofit.create(ApiRetrofit::class.java)

    fun getRequest(path: String, customCallback: CustomCallback) {
        val call = service.getRequest(path)
        call.enqueue(callback(customCallback))
    }

    fun getRequest(path: String, path1: String, customCallback: CustomCallback) {
        val call = service.getRequest(path, path1)
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
    @GET("{path}")
    fun getRequest(@Path("path") path: String): Call<String>

    @GET("{path}/{path1}")
    fun getRequest(@Path("path") path: String, @Path("path1") path1: String): Call<String>
}