package com.example.core.repository.models.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.PagingItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_users")
data class FavoriteUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
            val login: String?,
            val itemId: Int? ,
            val avatarUrl: String?,
            val score: Double?, ): PagingItem