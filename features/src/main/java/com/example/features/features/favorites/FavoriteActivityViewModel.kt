package com.example.features.features.favorites

import com.example.common.schedulers.CommonSchedulers
import com.example.features.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteActivityViewModel @Inject constructor(schedulers: CommonSchedulers) : BaseViewModel(schedulers) {
}