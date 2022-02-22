package com.example.core.repository.models.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.repository.models.user.Item

@Entity(tableName = "favorite_users")
data class FavoriteUser(@PrimaryKey(autoGenerate = true) val id: Int? = null) : Item()