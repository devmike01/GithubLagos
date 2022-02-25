package com.example.features.utils.interfaces

import com.example.core.repository.models.favorite.FavoriteUser

interface OnClickUserListener {
    fun onClickUser(login: String)
}

interface OnFavoriteClickListener {
    fun onFavoriteClick(favoriteUser: FavoriteUser)
}