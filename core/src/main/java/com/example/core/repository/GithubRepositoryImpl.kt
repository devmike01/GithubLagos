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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException
import javax.inject.Inject

class GithubRepositoryImpl constructor(private val apiService: GithubApiService,
                                               private val scheduler: CoreSchedulers,
                                               private val favoriteDao: FavouriteUsersDao) : GithubRepository {


    override fun executeGetUsers(): GithubPagingSource{

        return GithubPagingSource(service = apiService)
    }

    override fun executeGetUserById(login: String): Single<DetailItemResponse> {
        return apiService.getUserByName(login)
    }

    override fun executeSaveFavoriteUser(user: FavoriteUser): Single<String> {

        return favoriteDao.saveFavourite(user).map {
            if(it > 0){
                "Favorite was saved successfully"
            }else{
                throw IllegalArgumentException("Adding user to favorite has failed")
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return favoriteDao.getFavoriteById(id)
    }

    override fun executeGetFavorites(): Single<List<FavoriteUser>> {
        return favoriteDao.getFavoriteUsers()
    }
}