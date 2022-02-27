package com.example.features.repository

import com.example.common.extensions.toSingle
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import io.reactivex.Flowable
import io.reactivex.Single

class FavoriteUserDaoTest : FavouriteUsersDao {

    private val fakeData = hashMapOf<Int, FavoriteUser>()

    private var isFailTest: Boolean = false

    companion object {

    fun getFavorite(id: Int = 0): FavoriteUser {
        return FavoriteUser(
            id = id,
            login = "Gbenga $id",
            itemId = id,
            avatarUrl = "http://hello.com/$id.jpg",
            score = 2.0
        )
    }
}

    override fun getFavoriteUsers(): Flowable<List<FavoriteUser>> {
        return if(isFailTest){
            Flowable.just(fakeData.values.toList())
        }else{
            Flowable.error(Exception("Github service has failed!"))
        }
    }

    override fun getFavoriteById(id: Int): Single<FavoriteUser> {
       return if(isFailTest){
           Single.error(Exception("Get favorite by id has failed"))
       }else{
           getFavorite(id).toSingle()
       }
    }

    override fun saveFavourite(favoriteUser: FavoriteUser): Single<Long> {
        return if(isFailTest){
            Single.error(Exception("Get favorite by id has failed"))
        }else{
            if(favoriteUser.id != null){
                fakeData[favoriteUser.id!!] = favoriteUser
                Single.just(1)
            }else{
                Single.just(0)
            }
        }
    }

    override fun deleteUserById(id: Int) {
        fakeData.remove(id)
    }

    override fun delete(user: FavoriteUser) {
        val tempMap = fakeData;
        tempMap.forEach {
            if (it.value == user){
                fakeData.values.remove(user)
            }
        }
    }

    override fun deleteAllUsers() {
        fakeData.clear()
    }
}