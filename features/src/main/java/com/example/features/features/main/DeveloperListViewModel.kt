package com.example.features.features.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.user.Item
import com.example.core.repository.models.user.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DeveloperListViewModel @Inject constructor(private val repository: GithubRepository,
                                                 private val scheduler: CommonSchedulers) : BaseViewModel(scheduler) {

    private val _users = MutableStateFlow<UiStates<PagingData<Item>>>(UiStates.loading())
    val users : Flow<UiStates<PagingData<Item>>> get() = _users

    fun fetchUsers(page: Int){
        _users.value = UiStates.loading()
        launchOnMain(repository.executeGetUsers(page), {
            _users.value = UiStates.error(it.message)
        }, {
            _users.value = UiStates.success(it)
        })
    }
}

