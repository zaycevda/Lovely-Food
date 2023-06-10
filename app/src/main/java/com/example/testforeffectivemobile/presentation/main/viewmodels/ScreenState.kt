package com.example.testforeffectivemobile.presentation.main.viewmodels

private typealias OnError = (Throwable) -> Unit
private typealias OnLoading = () -> Unit
private typealias OnSuccess<T> = (T) -> Unit

sealed class ScreenState<T> {

    class ErrorScreenState<T>(private val throwable: Throwable) : ScreenState<T>() {
        private var isShowedError = false

        fun onError(onError: OnError) {
            if (!isShowedError) {
                onError(throwable)
                isShowedError = true
            }
        }
    }

    class LoadingScreenState<T> : ScreenState<T>()
    class SuccessScreenState<T>(val data: T) : ScreenState<T>()

    fun on(
        error: OnError = {},
        loading: OnLoading = {},
        success: OnSuccess<T> = {}
    ) {
        when (this) {
            is ErrorScreenState -> onError(onError = error)
            is LoadingScreenState -> loading()
            is SuccessScreenState -> success(data)
        }
    }
}