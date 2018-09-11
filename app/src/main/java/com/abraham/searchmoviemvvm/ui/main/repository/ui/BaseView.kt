package com.abraham.searchmoviemvvm.ui.main.repository.ui

/**
 * Created by Abraham on 11/09/2018.
 */
interface BaseView<T>{
    fun setViewModel(viewModel: T)
    fun showProgressBar(active: Boolean)
    fun showEmpty(message: String?)

    fun onUnknownError(code: Int, errorMessage: String?)

    fun onTimeout()

    fun onNetworkError()
}