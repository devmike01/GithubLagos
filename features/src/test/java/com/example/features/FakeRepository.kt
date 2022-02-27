package com.example.features

import com.example.common.extensions.toSingle
import com.example.core.repository.GithubRepository
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.paging.FavoritePagingSource
import com.example.core.repository.paging.GithubPagingSource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException

class FakeRepository(private val apiService: FakeGithubApiService, private val db: FavouriteUsersDao) : GithubRepository {

    override fun executeGetUsers(): GithubPagingSource {
        return GithubPagingSource(service = apiService)
    }

    override fun executeGetFavoritePagingSource(): FavoritePagingSource {
        return FavoritePagingSource(db)
    }

    override fun executeGetUserById(login: String): Single<DetailItemResponse> {
        return apiService.getUserByName(login)
    }

    override fun executeSaveFavoriteUser(user: FavoriteUser): Single<String> {
        return db.saveFavourite(user).map {
            if(it > 0){
                "Favorite was saved successfully"
            }else{
                throw IllegalArgumentException("Adding user to favorite has failed")
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser> {
        return db.getFavoriteById(id)
    }

    override fun executeGetFavorites(): Flowable<List<FavoriteUser>> {
        return db.getFavoriteUsers()
    }

    override fun executeDeleteUserById(id: Int): Single<Unit> {
        return db.deleteUserById(id).toSingle()
    }

    override fun executeDeleteAllFavorites(): Single<Unit> {
        return db.deleteAllUsers().toSingle()
    }
}