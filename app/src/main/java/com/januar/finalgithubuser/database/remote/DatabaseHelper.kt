package com.januar.finalgithubuser.database.remote

import android.content.Context
import androidx.room.Room
import com.januar.finalgithubuser.database.local.FavouriteDatabase

object DatabaseHelper {
    fun getFavouriteDB(context: Context): FavouriteDatabase?{
        return Room.databaseBuilder(context, FavouriteDatabase::class.java, "favorite")
            .allowMainThreadQueries().build()
    }
}