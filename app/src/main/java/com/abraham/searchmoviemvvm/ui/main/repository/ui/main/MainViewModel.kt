package com.abraham.searchmoviemvvm.ui.main.repository.ui.main

import android.arch.lifecycle.ViewModel
import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource.SearchDataSource
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseView
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource.RemoteCallback


class MainViewModel(val repository: SearchDataSource) : ViewModel(){

    var list: MutableLiveData<SearchResult> = object :MutableLiveData<SearchResult>(){}

    fun findUsers(query: String,view:BaseView) {
        view.showProgressBar(true)
        repository.findUser(query,1,view,object :RemoteCallback.Load<SearchResult>{
            override fun onDataLoaded(data: SearchResult) {
                list.value=data
                view.showProgressBar(false)
            }
        })
    }

    fun findUsers(query: String, page: Int,view:BaseView) {
        view.showProgressBar(true)
        repository.findUser(query,page,view,object :RemoteCallback.Load<SearchResult>{
            override fun onDataLoaded(data: SearchResult) {
                list.value=data
                view.showProgressBar(false)
            }
        })
    }

    fun getListLiveData(): LiveData<SearchResult> {
        return list
    }
}