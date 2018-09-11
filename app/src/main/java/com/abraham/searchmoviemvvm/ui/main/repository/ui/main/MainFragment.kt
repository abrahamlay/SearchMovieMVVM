package com.abraham.searchmoviemvvm.ui.main.repository.ui.main

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseListFragment
import com.abraham.searchmoviemvvm.ui.main.repository.util.MyApplication

/**
 * Created by Abraham on 11/09/2018.
 */
class MainFragment:BaseListFragment(){
    val viewModel:MainViewModel = MainViewModel(this)

    override fun findData() {
        if (MyApplication.get(context).isInternetAvailable()) {
            if (pageToLoad === 1) {
                presenter.findUsers(searchView.getQuery().toString())
            } else {
                presenter.findUsers(searchView.getQuery().toString(), pageToLoad)
            }
        } else {
            onNetworkError()
        }

    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun setViewModel(viewModel: MainViewModel) {
        viewModel
    }

}