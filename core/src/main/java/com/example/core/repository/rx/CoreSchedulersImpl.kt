package com.example.core.repository.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface   CoreSchedulers {
    fun io(): Scheduler
}

class CoreSchedulersImpl @Inject constructor() : CoreSchedulers{
    override fun io(): Scheduler {
        return Schedulers.io()
    }

}