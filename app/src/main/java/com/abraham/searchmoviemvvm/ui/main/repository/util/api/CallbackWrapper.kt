package com.abraham.searchmoviemvvm.ui.main.repository.util.api

import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseView
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Abraham on 11/09/2018.
 */
 abstract class CallbackWrapper<T : SearchResult>(private var view: BaseView<Any>) : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(t: Throwable) {
        var code = 0
        if (t is HttpException) {

            val responseBody = t.response().errorBody()
            code = t.response().code()
            view.showProgressBar(false)
            view.onUnknownError(code, getErrorMessage(responseBody))
        } else if (t is SocketTimeoutException) {
            view.showProgressBar(false)
            view.onTimeout()
        } else if (t is IOException) {
            view.showProgressBar(false)
            view.onNetworkError()
        } else {
            view.showProgressBar(false)
            view.onUnknownError(code, t.message)
        }
    }

    override fun onComplete() {

    }

    protected abstract fun onSuccess(t: T)

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        try {
            val jsonObject = JSONObject(responseBody!!.string())
            return jsonObject.getString("message")
        } catch (e: Exception) {
            return e.message
        }

    }
}