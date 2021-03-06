package com.januar.consumerapp.api

import com.januar.consumerapp.utils.Value
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Value.URLData)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}