package com.example.core.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.core.repository.models.user.Item
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.rx.exts.toSingle
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

const val GITHUB_PAGE_INDEX: Int =1
open class GithubPagingSource @Inject constructor(private val service: GithubApiService) : RxPagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let{anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Item>> {

        val position = params.key ?: GITHUB_PAGE_INDEX

        return loadPage(position)

    }

    private fun loadPage(position: Int): Single<LoadResult<Int, Item>> {

        return try {
            val users: List<Item> = service.getUsers(page = position)
                .subscribeOn(Schedulers.io()).blockingGet().items ?: emptyList()
            val nextKey = if (users.isEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = users,
                prevKey = if (position == GITHUB_PAGE_INDEX) null else position - 1,
                nextKey
            )
        }catch (exception: Exception){
            LoadResult.Error(Exception("Error fetching users. Please check your internet and try again."))
        }.toSingle()
    }
}

/*


        return service.getUsers(page = params.key ?: page).map { userResponse ->
            val position = params.key ?: GITHUB_PAGE_INDEX


            try {
                val users: List<Item> = userResponse.items ?: emptyList()
                val nextKey = if (users.isEmpty()) {
                    null
                } else {
                    position + 1
                }

                LoadResult.Page(
                    data = users,
                    prevKey = if (position == GITHUB_PAGE_INDEX) null else position - 1,
                    nextKey
                )
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            } catch (exception: HttpException) {
                Log.d("onBindViewHolder_" , "${params.key} __ $exception")
                LoadResult.Error(exception)
            }
        }.subscribeOn(Schedulers.io())

   override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: GITHUB_PAGE_INDEX
        return try {
            val userResponse = service.getUsers(page = position.toString())
            val users : List<Item> = userResponse.blockingGet().items ?: emptyList()
            val nextKey = if(users.isEmpty()){
                null
            }else{
                position + (params.loadSize / GITHUB_PAGE_INDEX)
            }

            LoadResult.Page(
                data = users,
                prevKey = if(position == GITHUB_PAGE_INDEX) null else position-1,
                nextKey
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
 */