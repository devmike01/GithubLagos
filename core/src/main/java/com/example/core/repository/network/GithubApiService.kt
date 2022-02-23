package com.example.core.repository.network

import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET(ApiEndpoints.SEARCH_USERS)
    fun getUsers(@Query("q") location: String = "lagos",
                 @Query("page") page: String) : Single<UserResponse>

    @GET("/${ApiEndpoints.USERS}/{username}")
    fun getUserByName(@Path("username") username: String): Single<DetailItemResponse>
}