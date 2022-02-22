package com.example.common.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object CommonSchedulers {
    fun io() = Schedulers.io()
    fun mainThread() = AndroidSchedulers.mainThread()
}