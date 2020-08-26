package com.januar.finalgithubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.finalgithubuser.database.repository.DetailRepository
import com.januar.finalgithubuser.model.UserDetails

class DetailViewModel : ViewModel(){
    private var detailData: MutableLiveData<UserDetails> = MutableLiveData()

    fun setDetail(username: String){
        DetailRepository.init(username)
        detailData = DetailRepository.getUserDetails()
    }

    fun getDetail(): LiveData<UserDetails>{
        return detailData
    }
}