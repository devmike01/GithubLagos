package com.example.features.features.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.features.BaseViewModel
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.user.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeveloperListViewModel @Inject constructor(private val repository: GithubRepository,
                                                 private val scheduler: CommonSchedulers) : BaseViewModel(scheduler) {

    private val _users = MutableLiveData<UiStates<UserResponse>>()
    val users : LiveData<UiStates<UserResponse>> get() = _users

    fun fetchUsers(page: Int){
        _users.value = UiStates.loading()
        launchOnMain(repository.executeGetUsers(page), {
            _users.value = UiStates.error(it.message)
        }, {
            Log.d("nnnnnnnnn", "nwe => $it")
            _users.value = UiStates.success(it)
        })
    }
}

