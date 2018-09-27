package com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource

import android.arch.lifecycle.LiveData
import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseView
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.ApiConfig
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.CallbackWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.arch.lifecycle.MutableLiveData



/**
 * Created by Abraham on 11/09/2018.
 */
class SearchDataSource(private val service: SearchAPI) {


    fun findUser(query: String, page: Int,
                 view: BaseView, listener: RemoteCallback.Load<SearchResult>){
        val compositeDisposable = CompositeDisposable()
        val disposable = service.searchUsers(ApiConfig.API_KEY, ApiConfig.LANG_SETTINGS, page, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<SearchResult>(view) {
                    override fun onSuccess(searchResult: SearchResult) {
                        listener.onDataLoaded(searchResult)
                    }
                })
        compositeDisposable.add(disposable)
    }
}