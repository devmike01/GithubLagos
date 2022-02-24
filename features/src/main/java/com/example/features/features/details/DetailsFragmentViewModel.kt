package com.example.features.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.user.DetailItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private val repository: GithubRepository,
                                                   schedulers: CommonSchedulers) : BaseViewModel(schedulers) {

    private val _detailUser = MutableLiveData<UiStates<DetailItemResponse>>()
    val detailUser : LiveData<UiStates<DetailItemResponse>> get() = _detailUser

    fun fetchDetails(login: String){
        _detailUser.value = UiStates.loading()
        launchOnMain(repository.executeGetUserById(login), {
            _detailUser.value = UiStates.error(it.message)
        }, {
            _detailUser.value = UiStates.success(it)
        })
    }
}