package com.januar.finalgithubuser.database.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.januar.finalgithubuser.model.Favorite


@Dao
interface FavouriteInterface{
    @Query("SELECT * FROM favorite")
    fun getCursor(): Cursor

    @Insert
    fun insertCursor(favorite: Favorite): Long

    @Query("DELETE FROM favorite WHERE id = :id")
    fun deleteCursor(id: Long): Int
}