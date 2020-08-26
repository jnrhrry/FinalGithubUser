package com.januar.consumerapp.database.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.januar.consumerapp.api.DataAPI
import com.januar.consumerapp.api.DataClient
import com.januar.consumerapp.model.data.DataSourceItem
import com.januar.consumerapp.utils.Value
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingRepository {
    companion object{
        private val followingData: MutableLiveData<List<DataSourceItem>> = MutableLiveData()

        fun init(username: String){
            val api: DataAPI = DataClient.retrofit.create(DataAPI::class.java)
            val call: Call<List<DataSourceItem>> = api.getFollowing(username, Value.API_KEY)

            call.enqueue(object : Callback<List<DataSourceItem>>{
                override fun onResponse(
                    call: Call<List<DataSourceItem>>,
                    response: Response<List<DataSourceItem>>
                ) {
                    followingData.value = response.body()
                }

                override fun onFailure(call: Call<List<DataSourceItem>>, t: Throwable) {
                    Log.e("Following", t.message.toString())
                }
            })
        }

        fun getFollowing(): MutableLiveData<List<DataSourceItem>>{
            return followingData
        }
    }
}