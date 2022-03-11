package com.example.features

import androidx.paging.PagingSource
import com.example.common.extensions.toSingle
import com.example.core.repository.GithubRepository
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.paging.GithubPagingSource
import com.example.features.utils.PagingTestSource
import io.reactivex.Single

open class FakeRepositoryImpl(private val apiService: GithubApiService,
                              private val githubPagingSource: GithubPagingSource,
                              private val db: FavouriteUsersDao) : GithubRepository {

    override fun executeGetUsers(): GithubPagingSource {
        return githubPagingSource
    }

    override fun executeGetFavoritePagingSource(): PagingSource<Int, FavoriteUser> {
        return PagingTestSource()
    }


    override fun executeGetUserById(login: String): Single<DetailItemResponse> {
        return apiService.getUserByName(login)
    }

    override fun executeFavoriteUser(user: FavoriteUser): Single<String> {

         return db
            .saveFavourite(user).blockingGet().let{
                 if(it >= 0){
                     Single.just("Favorite was saved successfully")
                 }else{
                     throw IllegalArgumentException("Adding user to favorite has failed")
                 }
             }
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return db.getFavoriteById(id)
    }

    override fun executeDeleteUserById(id: Int): Single<Unit> {
        return db.deleteUserById(id).toSingle()
    }

    override fun executeDeleteAllFavorites(): Single<Unit> {
        return db.deleteAllUsers().toSingle()
    }
}