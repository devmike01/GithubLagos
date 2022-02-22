package com.example.core.repository.network

import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/users")
    fun getUsers() : Single<UserResponse>

    @GET("/users/{username}")
    fun getUserByName(@Path("username") username: String): Single<Item>
}