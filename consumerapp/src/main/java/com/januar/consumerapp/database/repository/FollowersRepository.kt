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

class FollowersRepository {
    companion object{
        private val followersData: MutableLiveData<List<DataSourceItem>> = MutableLiveData()

        fun init(username: String){
            val api: DataAPI = DataClient.retrofit.create(DataAPI::class.java)
            val call: Call<List<DataSourceItem>> = api.getFollowers(username, Value.API_KEY)

            call.enqueue(object : Callback<List<DataSourceItem>>{
                override fun onResponse(
                    call: Call<List<DataSourceItem>>,
                    response: Response<List<DataSourceItem>>
                ) {
                    followersData.value = response.body()
                }

                override fun onFailure(call: Call<List<DataSourceItem>>, t: Throwable) {
                    Log.e("Followers", t.message.toString())
                }
            })
        }
        fun getFollowers(): MutableLiveData<List<DataSourceItem>> {
            return followersData
        }
    }
}