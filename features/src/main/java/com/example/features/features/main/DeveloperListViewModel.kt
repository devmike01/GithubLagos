package com.example.features.features.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import androidx.paging.rxjava2.observable
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DeveloperListViewModel @Inject constructor(private val repository: GithubRepository,
                                                 private val scheduler: CommonSchedulers) : BaseViewModel(scheduler) {

    private val _users = MutableLiveData<UiStates<PagingData<Item>>>()
    val users : LiveData<UiStates<PagingData<Item>>> get() = _users


    private val _favorite = MutableStateFlow<UiStates<String>>(UiStates.loading())
    val favorite : Flow<UiStates<String>> get() = _favorite

    fun fetchUsers(page: Int){
        _users.value = UiStates.loading()
        val githubSource = repository.executeGetUsers()

        launch(   Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false,),
            pagingSourceFactory = {
                githubSource
            }
        ).flowable, {
            Log.d("fetchUsers", "fetchUsers => $it")
            _users.value = UiStates.error(it.message)
        }, {
            _users.value = UiStates.success(it)
        })
    }

    fun favoriteUser(user: FavoriteUser){

        launch(repository.executeSaveFavoriteUser(user), {
            _favorite.value = UiStates.error(it.message)
        }, {
            _favorite.value = UiStates.success(it)
        })
    }
}

