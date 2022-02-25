package com.example.common.viewmodels

import androidx.lifecycle.ViewModel
import com.example.common.schedulers.CommonSchedulers
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(private val scheduler: CommonSchedulers) : ViewModel() {

    private val composable = CompositeDisposable()

    protected fun <S> launch(single: Single<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(single.subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe(onSuccess, onError))
    }

    protected fun <S> launch(single: Flowable<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(single.subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe(onSuccess, onError))
    }



    protected fun <S> launch(observable: Observable<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(observable.subscribeOn(scheduler.io()).observeOn(scheduler.ui())
            .subscribe(onSuccess, onError))
    }

    protected fun <S> launchOnMain(single: Single<S>, onError: (Throwable) -> Unit, onSuccess : (S) -> Unit){
        composable.add(single.observeOn(scheduler.ui())
            .subscribe(onSuccess, onError))
    }

    override fun onCleared() {
        super.onCleared()
        composable.clear()
    }
}