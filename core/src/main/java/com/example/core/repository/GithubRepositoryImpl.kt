package com.example.core.repository

import androidx.room.Dao
import com.example.common.schedulers.CommonSchedulers
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val apiService: GithubApiService,
                                               private val favoriteDao: FavouriteUsersDao) : GithubRepository {

    override fun executeGetUsers(): Single<UserResponse> {
        return apiService.getUsers().subscribeOn(CommonSchedulers.io())
    }

    override fun executeGetUserById(login: String): Single<Item> {
        return apiService.getUserByName(login).subscribeOn(CommonSchedulers.io())
    }

    override fun executeSaveFavoriteUser(user: FavoriteUser): Single<Boolean> {
        return favoriteDao.saveFavourite(user)
            .subscribeOn(CommonSchedulers.io()).map {
            it > 0
        }
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return favoriteDao.getFavoriteById(id).subscribeOn(CommonSchedulers.io())
    }
}