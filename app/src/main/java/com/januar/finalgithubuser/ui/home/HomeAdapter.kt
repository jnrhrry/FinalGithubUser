package com.januar.finalgithubuser.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.model.Favorite
import com.januar.finalgithubuser.model.data.DataSourceItem
import com.januar.finalgithubuser.ui.detail.DetailActivity
import com.januar.finalgithubuser.ui.dialog.FavouriteContext
import com.januar.finalgithubuser.utils.Value
import kotlinx.android.synthetic.main.list_user.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private lateinit var dataList: List<DataSourceItem>

    fun setData(dataList: List<DataSourceItem>){
        this.dataList = dataList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)

        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        Glide.with(holder.itemView.context).setDefaultRequestOptions(object : RequestOptions(){
            override fun placeholder(drawable: Drawable?): RequestOptions {
                return super.placeholder(R.color.colorPrimaryDark)
            }
        })
            .load(dataList[position].avatar_url)
            .into(holder.itemView.small_avatar)
        holder.itemView.username.text = dataList[position].login
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Value.mainData, dataList[position].login)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null) }
            holder.itemView.setOnLongClickListener {
            FavouriteContext.optionDialog(holder.itemView.context, Favorite(dataList[position].id, dataList[position].login, dataList[position].avatar_url)).show()

            return@setOnLongClickListener true
        }


    }

    inner class HomeViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
}