package com.januar.consumerapp.api

import com.januar.consumerapp.model.UserDetails
import com.januar.consumerapp.model.data.DataSourceItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface DataAPI{

    @GET("users/{username}")
    fun getDetails(@Path("username") username: String, @Header("Authorization") token: String): Call<UserDetails>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String, @Header("Authorization") token: String): Call<List<DataSourceItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String, @Header("Authorization") token: String): Call<List<DataSourceItem>>
}