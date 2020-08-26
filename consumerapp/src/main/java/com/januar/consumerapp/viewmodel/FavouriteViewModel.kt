package com.januar.consumerapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.januar.consumerapp.database.repository.FavouriteRepository
import com.januar.consumerapp.model.Favorite

class FavouriteViewModel : ViewModel(){

    fun setData(context: Context, favourite: Favorite): Long?{
        return FavouriteRepository.insert(context, favourite)
    }
    fun removeData(context: Context, favourite: Favorite){
        FavouriteRepository.delete(context, favourite)
    }
    fun getData(context: Context): LiveData<ArrayList<Favorite>>{
        return FavouriteRepository.getData(context)
    }
}