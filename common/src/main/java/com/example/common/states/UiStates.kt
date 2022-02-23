package com.example.common.states

sealed class Status{

    object Success : Status()
    object Failed : Status()
    object Loading : Status()
}

class UiStates<T> private constructor(status: Status, data: T?, message: String?) {
    val status: Status = status
    val data: T? = data
    val message: String? = message

    companion object {
        fun <T> success(data: T): UiStates<T> {
            return UiStates(Status.Success, data, null)
        }

        fun <T> error(msg: String?): UiStates<T> {
            return UiStates(Status.Failed, null, msg)
        }

        fun <T> loading(): UiStates<T> {
            return UiStates(Status.Loading, null, null)
        }
    }

}
