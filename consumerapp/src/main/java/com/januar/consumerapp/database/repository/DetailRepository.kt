package com.januar.consumerapp.database.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.januar.consumerapp.api.DataAPI
import com.januar.consumerapp.api.DataClient
import com.januar.consumerapp.model.UserDetails
import com.januar.consumerapp.utils.Value
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {
    companion object{
        private val detailData: MutableLiveData<UserDetails> = MutableLiveData()

        fun init(username: String){
            val api: DataAPI = DataClient.retrofit.create(DataAPI::class.java)
            val call: Call<UserDetails> = api.getDetails(username, Value.API_KEY)

            call.enqueue(object : Callback<UserDetails>{
                override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                    detailData.value = response.body()
                }

                override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                    Log.e("Details", t.message.toString())
                }
            })
        }
        fun getUserDetails(): MutableLiveData<UserDetails>{
            return detailData
        }
    }
}