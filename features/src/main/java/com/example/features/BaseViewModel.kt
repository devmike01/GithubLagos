package com.example.features

import androidx.lifecycle.ViewModel
import com.example.common.schedulers.CommonSchedulers
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(private val scheduler: CommonSchedulers) : ViewModel() {

    private val composable = CompositeDisposable()

    fun <S> launchOnMain(single: Single<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(single.observeOn(scheduler.ui()).subscribe(onSuccess, onError))
    }

    override fun onCleared() {
        super.onCleared()
        composable.clear()
    }
}