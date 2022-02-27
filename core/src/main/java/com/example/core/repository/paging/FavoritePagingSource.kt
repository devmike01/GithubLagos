package com.example.core.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import com.example.core.repository.rx.exts.toSingle
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

//class FavoritePagingSource(val favoriteDao: FavouriteUsersDao) : RxPagingSource<Int, FavoriteUser>() {
//
//    override fun getRefreshKey(state: PagingState<Int, FavoriteUser>): Int? {
//        return state.anchorPosition?.let{anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FavoriteUser>> {
//
//        val position = params.key ?: GITHUB_PAGE_INDEX
//
//
//        return try {
//            val favUsers: List<FavoriteUser> = favoriteDao.getFavoriteUsers()
//                .subscribeOn(Schedulers.io()).blockingFirst() ?: emptyList()
//            val nextKey = if (favUsers.isEmpty()) {
//                null
//            } else {
//                position + 1
//            }
//
//            LoadResult.Page(
//                data = favUsers,
//                prevKey = if (position == GITHUB_PAGE_INDEX) null else position - 1,
//                nextKey
//            )
//        }catch (exception: Exception){
//            LoadResult.Error(Exception("Error fetching users. Please check your internet and try again."))
//        }.toSingle()
//    }
//}