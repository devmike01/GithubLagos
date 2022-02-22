package com.example.common.viewmodels

import androidx.lifecycle.ViewModel
import com.example.common.schedulers.CommonSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel(private val scheduler: CommonSchedulers) : ViewModel() {

    private val composable = CompositeDisposable()

    fun <S> launchOnMain(single: Single<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(single.observeOn(CommonSchedulers.mainThread()).subscribe(onSuccess, onError))
    }

    override fun onCleared() {
        super.onCleared()
        composable.clear()
    }
}