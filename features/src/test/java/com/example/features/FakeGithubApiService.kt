package com.example.features

import com.example.core.repository.models.user.DetailItemResponse
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import com.example.core.repository.network.GithubApiService
import io.reactivex.Single

open class FakeGithubApiService : GithubApiService {

    override fun getUsers(location: String, page: Int): Single<UserResponse> {
        val responses = arrayListOf<Item>()
        for (i in 0..2){
            responses.add(Item(
                itemId = i,
                login = "name $i",
                avatarUrl = "img.com/$i.jpg",
                score = i.toDouble()
            ))
        }
        return Single.just(UserResponse(
            items = responses
        ))
    }

    override fun getUserByName(username: String): Single<DetailItemResponse> {
        TODO("Not yet implemented")
    }
}