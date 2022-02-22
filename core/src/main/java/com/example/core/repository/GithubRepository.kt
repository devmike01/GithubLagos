package com.example.core.repository

import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import io.reactivex.Single

interface GithubRepository {

    fun executeGetUsers(): Single<UserResponse>

    fun executeGetUserById(login: String): Single<Item>

    fun executeSaveFavoriteUser(user: FavoriteUser): Single<Boolean>

    fun executeGetFavoriteUserById(id: Int): Single<FavoriteUser>
}