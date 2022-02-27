package com.example.features.features.favorites

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.favorite.FavoriteUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class Favorite(val msg: String, val position: Int)

@HiltViewModel
class FavoriteActivityViewModel @Inject constructor(schedulers: CommonSchedulers, private val repository: GithubRepository) : BaseViewModel(schedulers) {

    private var _favorites : MutableStateFlow<UiStates<PagingData<FavoriteUser>>> =MutableStateFlow( UiStates.loading())
    val favorites : Flow<UiStates<PagingData<FavoriteUser>>> get() = _favorites


    private var _deleteFavorites : MutableStateFlow<UiStates<Favorite>> =MutableStateFlow( UiStates.loading())
    val deleteFavorites : Flow<UiStates<Favorite>> get() = _deleteFavorites

    private var _deleteAllFavorites : MutableStateFlow<UiStates<String>> =MutableStateFlow( UiStates.loading())
    val deleteAllFavorites : Flow<UiStates<String>> get() = _deleteAllFavorites

    fun fetchFavorites(){
        _favorites.value = UiStates.loading()

        launch(
            Pager(
                config = PagingConfig(pageSize = 30, enablePlaceholders = false,
                    prefetchDistance = 5),
                pagingSourceFactory = {
                    repository.executeGetFavoritePagingSource()
                }
            ).flowable, {
                Log.d("fetchUsers", "fetchUsers => $it")
                _favorites.value = UiStates.error(it.message)
            }, {
                _favorites.value = UiStates.success(it)
            })
    }

    fun deleteAllFavorites(){
        launch(repository.executeDeleteAllFavorites(), {
                 _deleteAllFavorites.value = UiStates.error(it.message)
        }, {
            _deleteAllFavorites.value = UiStates.success("Favorite was cleared!")
        })
    }

    fun deleteFavorite(id: Int, position: Int){
        launch(repository.executeDeleteUserById(id), {
            _deleteFavorites.value = UiStates.error(it.message)
        }, {
            _deleteFavorites.value = UiStates.success(Favorite("You have removed this user", position))
        })
    }
}