package com.januar.finalgithubuser.database.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.januar.finalgithubuser.database.local.FavouriteInterface
import com.januar.finalgithubuser.database.remote.DatabaseContract
import com.januar.finalgithubuser.database.remote.DatabaseHelper
import com.januar.finalgithubuser.model.Favorite


class FavouriteProvider : ContentProvider(){

    companion object{
        private const val FAV = 1
        private const val FAV_ID = 2
        lateinit var favouriteInt: FavouriteInterface
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.FavouriteColumns.TABLE_NAME, FAV)
            sUriMatcher.addURI(DatabaseContract.AUTHORITY, "${DatabaseContract.FavouriteColumns.TABLE_NAME}/#", FAV_ID)
        }
    }
    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        val add: Long = when (FAV){
            sUriMatcher.match(p0) -> favouriteInt.insertCursor(
                Favorite(p1?.getAsLong(DatabaseContract.FavouriteColumns.ID)!!,
                p1.getAsString(DatabaseContract.FavouriteColumns.LOGIN),
                p1.getAsString(DatabaseContract.FavouriteColumns.AVATAR_URL))
            )
            else -> 0
        }
        context?.contentResolver?.notifyChange(DatabaseContract.FavouriteColumns.CONTENT_URI, null)
        return Uri.parse("${DatabaseContract.FavouriteColumns.CONTENT_URI}/$add")
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return if (sUriMatcher.match(p0) == FAV) favouriteInt.getCursor() else null
    }

    override fun onCreate(): Boolean {
        favouriteInt = DatabaseHelper.getFavouriteDB(context as Context)!!.favouriteInt()
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<String>?): Int {
        context?.contentResolver?.notifyChange(DatabaseContract.FavouriteColumns.CONTENT_URI, null)
        return favouriteInt.deleteCursor(p0.lastPathSegment.toString().toLong())
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

}