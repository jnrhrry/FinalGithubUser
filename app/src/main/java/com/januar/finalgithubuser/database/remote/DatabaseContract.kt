package com.januar.finalgithubuser.database.remote

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.januar.finalgithubuser"
    const val SCHEME = "content"

    class FavouriteColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}