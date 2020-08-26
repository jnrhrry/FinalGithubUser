package com.januar.consumerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.consumerapp.database.repository.FollowingRepository
import com.januar.consumerapp.model.data.DataSourceItem

class FollowingViewModel : ViewModel(){

    private var followingData: MutableLiveData<List<DataSourceItem>> = MutableLiveData()

    fun setFollowing(username: String){
        FollowingRepository.init(username)
        followingData = FollowingRepository.getFollowing()
    }

    fun getFollowing(): LiveData<List<DataSourceItem>>{
        return followingData
    }
}