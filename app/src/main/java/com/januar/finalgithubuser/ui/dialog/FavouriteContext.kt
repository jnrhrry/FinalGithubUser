package com.januar.finalgithubuser.ui.dialog

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.model.Favorite
import com.januar.finalgithubuser.viewmodel.FavouriteViewModel

class FavouriteContext {
    companion object{
        fun optionDialog(context: Context, favorite: Favorite):AlertDialog.Builder{
            return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.add_to_favourite))
                .setMessage(context.resources.getString(R.string.add_to_favourite_text))
                .setPositiveButton(context.resources.getString(R.string.why_not)){ _, _ ->
                    val dialog= FavouriteViewModel().setData(context, favorite) ?: -1
                    if (dialog > 0) Toast.makeText(context, context.resources.getString(R.string.add_to_favourite), Toast.LENGTH_SHORT).show()
                    else Toast.makeText(context, context.resources.getString(R.string.add_to_favourite_unable), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(context.resources.getString(R.string.add_to_favourite_already_exist)){ _, _ ->
                    Toast.makeText(context, context.resources.getString(R.string.add_to_favourite_unable), Toast.LENGTH_SHORT).show()
                }
                .setCancelable(false)
        }
    }
}