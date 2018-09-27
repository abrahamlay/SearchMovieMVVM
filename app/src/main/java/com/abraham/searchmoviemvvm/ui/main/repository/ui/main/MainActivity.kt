package com.abraham.searchmoviemvvm.ui.main.repository.ui.main

import android.os.Bundle
import com.abraham.searchmoviemvvm.R
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MainFragment(),false);
    }
}
