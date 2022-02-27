package com.example.core.repository.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.core.repository.models.favorite.FavoriteUser
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface FavouriteUsersDao {
    @Query("SELECT * FROM favorite_users ORDER BY id ASC LIMIT 10")
    fun getFavoriteUsers(): PagingSource<Int, FavoriteUser>

    @Query("SELECT * FROM favorite_users WHERE id =:id")
    fun getFavoriteById(id: Int): Single<FavoriteUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFavourite(favoriteUser: FavoriteUser): Single<Long>

    @Query("DELETE FROM favorite_users WHERE id =:id")
    fun deleteUserById(id: Int)


    @Query("DELETE FROM favorite_users")
    fun deleteAllUsers()

}