package com.example.core.repository.models.user

import com.example.core.repository.models.favorite.FavoriteUser

interface PagingItem
@Suppress("unchecked_cast")
fun <T> get(pagingItem : PagingItem) : T{
    return pagingItem as T
}