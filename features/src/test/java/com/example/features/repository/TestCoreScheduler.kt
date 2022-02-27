package com.example.features.repository

import com.example.core.repository.rx.CoreSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestCoreScheduler  : CoreSchedulers{
    override fun io(): Scheduler {
        return TestScheduler()
    }
}