package com.example.features

import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import io.reactivex.Single

class FakeGithubApiService : GithubApiService {

    override fun getUsers(location: String, page: Int): Single<UserResponse> {

        return Single.just(UserResponse(
            items = listOf(Item())
        ))
    }

    override fun getUserByName(username: String): Single<DetailItemResponse> {
        TODO("Not yet implemented")
    }
}