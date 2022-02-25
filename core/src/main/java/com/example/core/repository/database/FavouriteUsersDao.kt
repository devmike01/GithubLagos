package com.example.core.repository.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.repository.models.favorite.FavoriteUser
import io.reactivex.Single


@Dao
interface FavouriteUsersDao {
    @Query("SELECT * FROM favorite_users ORDER BY id ASC")
    fun getFavoriteUsers(): Single<List<FavoriteUser>>

    @Query("SELECT * FROM favorite_users WHERE id =:id")
    fun getFavoriteById(id: Int): Single<FavoriteUser>

    @Insert
    fun saveFavourite(favoriteUser: FavoriteUser): Single<Long>

}