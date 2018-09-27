package com.abraham.searchmoviemvvm.ui.main.repository.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource.SearchAPI
import com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource.SearchDataSource
import com.abraham.searchmoviemvvm.ui.main.repository.ui.main.MainViewModel
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.ApiConfig
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.RetrofitInstance
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){
    companion object {
        fun get(context: Context?): MyApplication {
            return context?.applicationContext as MyApplication
        }
    }

    // just declare it
    private val myModule = module {

        single { RetrofitInstance(ApiConfig.BASE_URL,
                GsonConverterFactory.create(ApiConfig.GSON),
                ApiConfig.rxAdapter).getInstance().create(SearchAPI::class.java)
                }
        single { SearchDataSource(get()) }

        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}