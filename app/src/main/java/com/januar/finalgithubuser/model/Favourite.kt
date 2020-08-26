package com.januar.finalgithubuser.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(@PrimaryKey val id: Long, val login: String?, val avatar_url:String)