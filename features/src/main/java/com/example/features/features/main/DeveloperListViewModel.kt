package com.example.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.common.viewmodels.BaseViewModel
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.user.UserResponse
import javax.inject.Inject

class DeveloperListViewModel @Inject constructor(private val repository: GithubRepository,
                                                 private val scheduler: CommonSchedulers) : BaseViewModel(scheduler) {




    fun fetchUsers(){
        launchOnMain<UiStates>(repository.executeGetUsers(), {

        }, {

        })
    }
}

// 600W$$&Sm@kes