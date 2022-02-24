package com.example.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.paging.GithubPagingSource
import com.example.core.repository.rx.CoreSchedulers
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl constructor(private val apiService: GithubApiService,
                                               private val scheduler: CoreSchedulers,
                                               private val favoriteDao: FavouriteUsersDao) : GithubRepository {


    override fun executeGetUsers(page: Int): Observable<PagingData<Item>> {

        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false, ),
            pagingSourceFactory = {GithubPagingSource(service = apiService, page= page)}
        ).observable
    }

    override fun executeGetUserById(login: String): Single<DetailItemResponse> {
        return apiService.getUserByName(login)
    }

    override fun executeSaveFavoriteUser(user: FavoriteUser): Single<Boolean> {

        return Single.just(favoriteDao.saveFavourite(user)).map {
            it > 0
        }
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return favoriteDao.getFavoriteById(id)
    }
}