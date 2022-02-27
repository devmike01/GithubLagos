package com.example.common.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CommonSchedulersImpl @Inject constructor() : CommonSchedulers{
    override  fun io() : Scheduler = Schedulers.io()
    override  fun ui() : Scheduler = AndroidSchedulers.mainThread()
}

interface CommonSchedulers{

    fun io() : Scheduler
    fun ui() :Scheduler
}

open class TestCommonSchedulers : CommonSchedulers {

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

}