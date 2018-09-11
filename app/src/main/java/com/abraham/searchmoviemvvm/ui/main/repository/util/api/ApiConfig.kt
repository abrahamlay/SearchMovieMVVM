package com.abraham.searchmoviemvvm.ui.main.repository.util.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by Abraham on 11/09/2018.
 */
object ApiConfig {
    val GSON = GsonBuilder()
            .create()
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "e1364e3bc8f9d46c4a09586973081f96"
    const val LANG_SETTINGS = "en-US"
    const val SEARCH_PATH = "search/movie?"

    const val NETWORK_TIMEOUT = 40

    val rxAdapter = RxJava2CallAdapterFactory.create()

    const val HTTP_UNPROCESSABLE_ENTITY = 422
}