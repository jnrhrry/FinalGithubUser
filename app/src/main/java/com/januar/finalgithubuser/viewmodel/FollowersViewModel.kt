package com.januar.finalgithubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.finalgithubuser.database.repository.FollowersRepository
import com.januar.finalgithubuser.model.data.DataSourceItem

class FollowersViewModel : ViewModel(){
    private var followersData: MutableLiveData<List<DataSourceItem>> = MutableLiveData()

    fun setFollowers(username: String){
        FollowersRepository.init(username)
        followersData = FollowersRepository.getFollowers()
    }
    fun getFollowers(): LiveData<List<DataSourceItem>>{
        return followersData
    }

}