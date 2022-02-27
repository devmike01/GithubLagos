package com.example.features.utils

import com.example.core.repository.rx.CoreSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestCoreScheduler  : CoreSchedulers{
    override fun io(): Scheduler {
        return TestScheduler()
    }
}