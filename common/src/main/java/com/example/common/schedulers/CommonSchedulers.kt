package com.example.common.schedulers

import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rx.Scheduler

object CommonSchedulers {
    fun io() : Scheduler = Schedulers.io()
    fun mainThread() = AndroidSchedulers.mainThread()
}