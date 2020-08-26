package com.januar.finalgithubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.finalgithubuser.database.repository.DataSourceRepository
import com.januar.finalgithubuser.model.data.DataSource

class HomeViewModel : ViewModel(){
    private var data : MutableLiveData<DataSource> = MutableLiveData()

    fun setLiveData(username: String){
        DataSourceRepository.init(username)
        data = DataSourceRepository.getDataSource()
    }
    fun getLiveData(): LiveData<DataSource>{
        return data
    }
}