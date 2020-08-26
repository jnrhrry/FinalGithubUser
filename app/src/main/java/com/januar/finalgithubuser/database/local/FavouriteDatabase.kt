package com.januar.finalgithubuser.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.januar.finalgithubuser.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase(){
    abstract fun favouriteInt(): FavouriteInterface
}