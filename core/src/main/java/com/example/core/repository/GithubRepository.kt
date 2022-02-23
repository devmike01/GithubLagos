package com.example.core.repository

import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import io.reactivex.Single

interface GithubRepository {

    fun executeGetUsers(page: Int): Single<UserResponse>

    fun executeGetUserById(login: String): Single<DetailItemResponse>

    fun executeSaveFavoriteUser(user: FavoriteUser): Single<Boolean>

    fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser>
}