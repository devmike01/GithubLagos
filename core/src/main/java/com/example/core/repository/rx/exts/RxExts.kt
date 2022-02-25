package com.example.core.repository.rx.exts

import io.reactivex.Single


@Suppress("unchecked_cast")
fun <T> Any.toSingle(): Single<T> {
    return Single.just(this as T)
}