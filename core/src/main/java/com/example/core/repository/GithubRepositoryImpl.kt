package com.example.core.repository

import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.rx.CoreSchedulers
import com.example.core.repository.rx.CoreSchedulersImpl
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubRepositoryImpl constructor(private val apiService: GithubApiService,
                                               private val scheduler: CoreSchedulers,
                                               private val favoriteDao: FavouriteUsersDao) : GithubRepository {

    override fun executeGetUsers(): Single<UserResponse> {
        return apiService.getUsers().subscribeOn(scheduler.io())
    }

    override fun executeGetUserById(login: String): Single<Item> {
        return apiService.getUserByName(login).subscribeOn(scheduler.io())
    }

    override fun executeSaveFavoriteUser(user: FavoriteUser): Single<Boolean> {

        return Single.just(favoriteDao.saveFavourite(user))
            .subscribeOn(scheduler.io()).map {
            it > 0
        }
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return favoriteDao.getFavoriteById(id).subscribeOn(scheduler.io())
    }
}