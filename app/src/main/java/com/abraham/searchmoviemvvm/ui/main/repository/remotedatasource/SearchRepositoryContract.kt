package com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource

import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseView

/**
 * Created by Abraham on 11/09/2018.
 */
interface SearchRepositoryContract{
    abstract fun findUser(query: String, page: Int, view: BaseView, callback: RemoteCallback.Load<SearchResult>)
}