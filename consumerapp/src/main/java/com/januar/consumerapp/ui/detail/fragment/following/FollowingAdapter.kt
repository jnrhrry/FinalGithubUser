package com.januar.consumerapp.ui.detail.fragment.following

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.januar.consumerapp.R
import com.januar.consumerapp.model.Favorite
import com.januar.consumerapp.model.data.DataSourceItem
import com.januar.consumerapp.ui.detail.DetailActivity
import com.januar.consumerapp.ui.dialog.FavouriteContext
import com.januar.consumerapp.utils.Value
import kotlinx.android.synthetic.main.list_user.view.*

class FollowingAdapter (private val followingList: List<DataSourceItem>):
        RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)

        return FollowingViewHolder(view)
    }

    override fun getItemCount(): Int = followingList.size

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(followingList[position].avatar_url).into(holder.itemView.small_avatar)
        holder.itemView.username.text = followingList[position].login
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundling = Bundle()
            bundling.putString(Value.mainData, followingList[holder.adapterPosition].login)
            intent.putExtras(bundling)
            ContextCompat.startActivity(it.context, intent, null)
        }
        holder.itemView.setOnLongClickListener {
            FavouriteContext.optionDialog(holder.itemView.context,
                Favorite(
                    followingList[position].id,
                    followingList[position].login,
                    followingList[position].avatar_url
                )
            ).show()

            return@setOnLongClickListener true
        }
    }

    inner class FollowingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}