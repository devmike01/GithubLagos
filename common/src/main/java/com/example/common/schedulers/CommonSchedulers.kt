package com.example.common.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommonSchedulersImpl @Inject constructor() : CommonSchedulers{
    override  fun io() : Scheduler = Schedulers.io()
    override  fun ui() : Scheduler = AndroidSchedulers.mainThread()
}

interface CommonSchedulers{

    fun io() : Scheduler
    fun ui() :Scheduler
}