package com.januar.consumerapp.database.repository

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import com.januar.consumerapp.database.remote.DatabaseContract
import com.januar.consumerapp.database.remote.MappingHelper
import com.januar.consumerapp.model.Favorite

class FavouriteRepository {
    companion object{
        private lateinit var favData: MutableLiveData<ArrayList<Favorite>>

        fun insert(context: Context, favourite: Favorite): Long?{
            try {
                val values = contentValuesOf()
                values.put(DatabaseContract.FavouriteColumns.ID, favourite.id)
                values.put(DatabaseContract.FavouriteColumns.LOGIN, favourite.login)
                values.put(DatabaseContract.FavouriteColumns.AVATAR_URL, favourite.avatar_url)

                context.contentResolver.insert(DatabaseContract.FavouriteColumns.CONTENT_URI, values)
                return favourite.id
            } catch (e: Exception){
                return -1
            }
        }

        fun delete(context: Context, favourite: Favorite){
            val uriId = Uri.parse(DatabaseContract.FavouriteColumns.CONTENT_URI.toString() + "/" + favourite.id)
            val value = ContentValues()
            value.put(DatabaseContract.FavouriteColumns.LOGIN, favourite.login)
            value.put(DatabaseContract.FavouriteColumns.AVATAR_URL, favourite.avatar_url)
            context.contentResolver.delete(uriId, null, null)
        }
        fun getData(context: Context): MutableLiveData<ArrayList<Favorite>>{
            favData = MutableLiveData()
            favData.value = MappingHelper.mapCursorToArrayList(
                context.contentResolver.query(DatabaseContract.FavouriteColumns.CONTENT_URI,
                null, null, null, null))
            return favData
        }
    }
}