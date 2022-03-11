package com.example.features.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.common.extensions.toSingle
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import io.reactivex.Flowable
import io.reactivex.Single

open class FakeFavoriteUserDao : FavouriteUsersDao {

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

    override fun getFavoriteUsers(): PagingSource<Int, FavoriteUser> {

        return PagingTestSource()

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
                Single.just(1)
            }else{
                Single.just(0)
            }
        }
    }

    override fun deleteUserById(id: Int) {
    }

    override fun deleteAllUsers() {
    }
}

open class PagingTestSource : RxPagingSource<Int, FavoriteUser>(){

    override fun getRefreshKey(state: PagingState<Int, FavoriteUser>): Int {
        return 0
    }

    override fun loadSingle(params: LoadParams<Int>):
            Single<LoadResult<Int, FavoriteUser>> {
        val mockFavoriteUsers  = arrayListOf<FavoriteUser>()
        for (i in 0..1){
            mockFavoriteUsers.add(FavoriteUser(i, "gbenga",
                i, "http", i.toDouble()))
        }
        return Single.just(
            LoadResult.Page(data = mockFavoriteUsers,
                nextKey = 12, prevKey = 11, itemsAfter = 0, itemsBefore = 3)
        )
    }
}

/*
Single.just(
            LoadResult.Page(data = arrayListOf(),
                nextKey = 12, prevKey = 12, itemsAfter = 0, itemsBefore = 3)
        )
 */