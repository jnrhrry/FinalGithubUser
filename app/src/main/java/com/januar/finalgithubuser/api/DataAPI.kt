package com.januar.finalgithubuser.api


import com.januar.finalgithubuser.model.UserDetails
import com.januar.finalgithubuser.model.data.DataSource
import com.januar.finalgithubuser.model.data.DataSourceItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DataAPI{
    @GET("search/users")
    fun getDataSearch(@Query("q") username:String, @Header("Authorization") token:String):Call<DataSource>

    @GET("users/{username}")
    fun getDetails(@Path("username") username: String, @Header("Authorization") token:String):Call<UserDetails>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String, @Header("Authorization") token:String):Call<List<DataSourceItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String, @Header("Authorization") token:String):Call<List<DataSourceItem>>}