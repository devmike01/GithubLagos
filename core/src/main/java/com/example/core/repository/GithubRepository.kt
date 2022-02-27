package com.example.core.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.paging.GithubPagingSource
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.Flow

interface GithubRepository {

    fun executeGetUsers(): GithubPagingSource

    fun executeGetFavoritePagingSource(): PagingSource<Int, FavoriteUser>

    fun executeGetUserById(login: String): Single<DetailItemResponse>

    fun executeSaveFavoriteUser(user: FavoriteUser): Single<String>

    fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser>

   // fun executeGetFavorites(): Flowable<List<FavoriteUser>>

    fun executeDeleteUserById(id: Int) : Single<Unit>

    fun executeDeleteAllFavorites(): Single<Unit>
}