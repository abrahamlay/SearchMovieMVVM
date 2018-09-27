package com.abraham.searchmoviemvvm.ui.main.repository.util.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abraham on 11/09/2018.
 */
class RetrofitInstance(val baseUrl:String,
                       val converterFactory: GsonConverterFactory,
                       val rxAdapter: RxJava2CallAdapterFactory){
    fun getInstance():Retrofit{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(rxAdapter)
                .build()
    }


}