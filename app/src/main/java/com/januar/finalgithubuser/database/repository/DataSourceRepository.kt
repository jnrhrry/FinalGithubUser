package com.januar.finalgithubuser.database.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.januar.finalgithubuser.api.DataAPI
import com.januar.finalgithubuser.api.DataClient
import com.januar.finalgithubuser.model.data.DataSource
import com.januar.finalgithubuser.utils.Value
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceRepository {
    companion object{
        private val setData: MutableLiveData<DataSource> = MutableLiveData()

        fun init(username: String){
            val api: DataAPI = DataClient.retrofit.create(DataAPI::class.java)
            val call: Call<DataSource> = api.getDataSearch(username, Value.API_KEY)

            call.enqueue(object : Callback<DataSource>{

                override fun onFailure(call: Call<DataSource>, t: Throwable) {
                    Log.e("DATA", t.message.toString())
                }

                override fun onResponse(call: Call<DataSource>, response: Response<DataSource>) {
                    val dataSource: DataSource? = response.body()
                    setData.value = dataSource
                }


            })
        }
        fun getDataSource(): MutableLiveData<DataSource>{
            return setData
        }
    }
}