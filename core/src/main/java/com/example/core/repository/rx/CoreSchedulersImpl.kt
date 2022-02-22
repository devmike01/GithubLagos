package com.example.core.repository.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

interface CoreSchedulers {

    fun io(): Scheduler

}

class CoreSchedulersImpl : CoreSchedulers{
    override fun io(): Scheduler {
        return Schedulers.io()
    }

}