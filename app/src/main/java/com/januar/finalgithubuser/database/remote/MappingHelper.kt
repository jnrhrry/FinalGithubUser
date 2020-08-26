package com.januar.finalgithubuser.database.remote

import android.database.Cursor
import com.januar.finalgithubuser.model.Favorite

object MappingHelper {
    fun mapCursorToArrayList(favouriteCursor:Cursor?): ArrayList<Favorite>{
        val favList = ArrayList<Favorite>()

        favouriteCursor?.apply {
            while (moveToNext()){
                val id = getLong(getColumnIndexOrThrow("id"))
                val username = getString(getColumnIndexOrThrow("login"))
                val avatar = getString(getColumnIndexOrThrow("avatar_url"))

                favList.add(Favorite(id, username, avatar))
        }
        }
        return favList
    }
}