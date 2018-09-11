package com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource

import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.ApiConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abraham on 11/09/2018.
 */
interface SearchAPI{
    @GET(ApiConfig.SEARCH_PATH)
    abstract fun searchUsers(@Query("api_key") apiKey: String,
                             @Query("language") lang: String,
                             @Query("page") page: Int,
                             @Query("query") query: String): Observable<SearchResult>
}