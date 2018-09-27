package com.abraham.searchmoviemvvm.ui.main.repository.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.abraham.searchmoviemvvm.ui.main.repository.ui.BaseListFragment
import com.abraham.searchmoviemvvm.ui.main.repository.util.MyApplication
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.Toast
import com.abraham.searchmoviemvvm.ui.main.repository.model.ResultsItem
import com.abraham.searchmoviemvvm.ui.main.repository.model.SearchResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject


/**
 * Created by Abraham on 11/09/2018.
 */
class MainFragment:BaseListFragment(), MainAdapter.OnItemClickListener {

    private val viewModel: MainViewModel by inject()

    // TODO CHANGE TO DATA BINDING AND ADDING SCROLL TO LOAD MORE
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSearchView()
    }

    private fun initAdapter(searchResult: SearchResult?) {
        itemList=searchResult?.results as List<ResultsItem>?
        adapter = MainAdapter(itemList, this)
        rv_list.adapter = adapter
    }

    private fun addDataAdapter(searchResult: SearchResult?) {
        itemList?.plus(searchResult)
        adapter?.notifyDataSetChanged()
    }

    private var searchView: SearchView?=null

    private fun initSearchView() {
        searchView=activity?.sv_main
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                findData()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                initState()
                return true
            }

        })

        viewModel.getListLiveData().observe(this, Observer {
            if(it?.results?.size!!>0 && pageToLoad==1){
                initAdapter(it)
            }else{
                addDataAdapter(it)
            }
        })
    }


    override fun findData() {
        if (MyApplication.get(context).isInternetAvailable()) {
            if (pageToLoad == 1) {
                viewModel.findUsers(searchView?.query.toString(),this)
            } else {
                viewModel.findUsers(searchView?.query.toString(),pageToLoad,this)
            }
        } else {
            onNetworkError()
        }

    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun onItemClicked(view: View, data: Any?, position: Int) {
        val message=(data as ResultsItem).title
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}