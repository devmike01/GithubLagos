package com.example.common.states

sealed class UiStates{

    data class Success<T>(val data: T?): UiStates()
    data class Failed(val error: String) : UiStates()
    object Loading : UiStates()
}