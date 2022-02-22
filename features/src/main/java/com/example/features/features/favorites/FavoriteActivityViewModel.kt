package com.example.features.favorites

import com.example.common.schedulers.CommonSchedulers
import com.example.common.viewmodels.BaseViewModel
import com.example.core.repository.rx.CoreSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class FavoriteActivityViewModel(schedulers: CommonSchedulers) : BaseViewModel(schedulers) {
}