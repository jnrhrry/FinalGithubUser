package com.januar.finalgithubuser.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.model.Favorite
import com.januar.finalgithubuser.ui.detail.DetailActivity
import com.januar.finalgithubuser.utils.Value
import com.januar.finalgithubuser.viewmodel.FavouriteViewModel
import kotlinx.android.synthetic.main.list_user_favourite.view.*

class FavouriteAdapter(private val favList: ArrayList<Favorite>):
        RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteAdapter.FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user_favourite, parent, false)

        return FavouriteViewHolder(view)
    }


    override fun getItemCount(): Int = favList.size


    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(favList[position].avatar_url).into(holder.itemView.small_avatar_favourite)
        holder.itemView.username_favourite.text = favList[position].login

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Value.mainData, favList[holder.adapterPosition].login)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null)
        }
        holder.itemView.removeFavourite.setOnClickListener{
            FavouriteViewModel().removeData(it.context, Favorite(favList[position].id, favList[position].login, favList[position].avatar_url))
            favList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            Toast.makeText(it.context, it.context.resources.getString(R.string.favourite_removed), Toast.LENGTH_SHORT).show()
        }
    }

    inner class FavouriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}