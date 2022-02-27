package com.example.features.utils.interfaces

import com.example.core.repository.models.favorite.FavoriteUser

interface OnClickUserListener {
    fun onClickUser(login: String)
}

interface OnFavoriteClickListener {
    fun onFavoriteClick(favoriteUser: FavoriteUser)
}

interface OnDeleteFavoriteUserListener {
    fun onDeleteFavoriteUser(itemId: Int, position: Int)
}

//interface OnClickFavoriteUserListener {
//    fun OnClickFavoriteUser(login: String)
//}