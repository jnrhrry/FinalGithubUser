package com.januar.finalgithubuser.database.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.januar.finalgithubuser.database.remote.DatabaseHelper
import com.januar.finalgithubuser.database.remote.MappingHelper
import com.januar.finalgithubuser.model.Favorite

class FavouriteRepository {
    companion object{
        private lateinit var favData: MutableLiveData<ArrayList<Favorite>>

        fun insert(context: Context, favourite: Favorite): Long?{
            return try {
                DatabaseHelper.getFavouriteDB(context)?.favouriteInt()?.insertCursor(favourite)
            } catch (e: Exception){
                -1
            }
        }

        fun delete(context: Context, favourite: Favorite){
            DatabaseHelper.getFavouriteDB(context)?.favouriteInt()?.deleteCursor(favourite.id)
        }
        fun getData(context: Context): MutableLiveData<ArrayList<Favorite>>{
            favData = MutableLiveData()
            favData.value = MappingHelper.mapCursorToArrayList(
                DatabaseHelper.getFavouriteDB(context)?.favouriteInt()?.getCursor())
            return favData
        }
    }
}