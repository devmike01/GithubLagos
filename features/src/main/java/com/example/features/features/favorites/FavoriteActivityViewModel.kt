package com.example.features.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.schedulers.CommonSchedulers
import com.example.common.states.UiStates
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.GithubRepository
import com.example.core.repository.models.favorite.FavoriteUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class FavoriteActivityViewModel @Inject constructor(private val schedulers: CommonSchedulers, private val repository: GithubRepository) : BaseViewModel(schedulers) {

    private var _favorites : MutableStateFlow<UiStates<List<FavoriteUser>>> =MutableStateFlow( UiStates.loading())
    val favorites : Flow<UiStates<List<FavoriteUser>>> get() = _favorites

    fun fetchFavorites(){
       launch(repository.executeGetFavorites(), {
           _favorites.value = UiStates.error(it.message)
       }, {
           _favorites.value = UiStates.success(it)
       })
    }
}