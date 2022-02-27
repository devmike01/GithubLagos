package com.example.core.repository

import android.util.Log
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.paging.GithubPagingSource
import com.example.core.repository.rx.CoreSchedulers
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException
import javax.inject.Inject

open class GithubRepositoryImpl constructor(private val apiService: GithubApiService,
                                            private val schedulers: CoreSchedulers,
                                            private val githubPagingSource: GithubPagingSource,
                                               private val favoriteDao: FavouriteUsersDao) :
    GithubRepository {

    override fun executeGetUsers(): GithubPagingSource{

        return githubPagingSource
    }

    override fun executeGetFavoritePagingSource(): PagingSource<Int, FavoriteUser> {
        return favoriteDao.getFavoriteUsers()
    }

    override fun executeGetUserById(login: String): Single<DetailItemResponse> {
        return apiService.getUserByName(login)
    }

    override fun executeFavoriteUser(user: FavoriteUser): Single<String> {

        return favoriteDao.saveFavourite(user).map {
            if(it >= 0){
                "Favorite was saved successfully"
            }else{
                throw IllegalArgumentException("Adding user to favorite has failed")
            }
        }.subscribeOn(schedulers.io())
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return favoriteDao.getFavoriteById(id)
    }

    override fun executeDeleteUserById(id: Int) = Single.fromCallable {
        favoriteDao.deleteUserById(id)
    }

    override fun executeDeleteAllFavorites() = Single.fromCallable {
        favoriteDao.deleteAllUsers()
    }


}