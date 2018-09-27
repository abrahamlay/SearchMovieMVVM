package com.abraham.searchmoviemvvm.ui.main.repository.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.abraham.searchmoviemvvm.R
import com.abraham.searchmoviemvvm.ui.main.repository.model.ResultsItem
import com.abraham.searchmoviemvvm.ui.main.repository.ui.main.MainAdapter
import com.abraham.searchmoviemvvm.ui.main.repository.ui.main.MainViewModel
import com.abraham.searchmoviemvvm.ui.main.repository.ui.widget.EmptyViewHolder
import com.abraham.searchmoviemvvm.ui.main.repository.util.api.ApiConfig
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.net.HttpURLConnection

/**
 * Created by Abraham on 11/09/2018.
 */
abstract class BaseListFragment:Fragment(),BaseView{
    protected var adapter: RecyclerView.Adapter<*>? = null
    protected var itemList: List<ResultsItem>?=null
    protected var pageToLoad = 1

    protected lateinit var emptyViewHolder: EmptyViewHolder
    protected abstract fun findData()

    protected abstract fun getLayoutManager(): RecyclerView.LayoutManager


    fun initState() {
        pageToLoad = 1
        adapter = null
        emptyViewHolder.hide()
        rv_list.layoutManager = getLayoutManager()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val emptyView = view.findViewById<View>(R.id.empty_view)
        emptyViewHolder = EmptyViewHolder(emptyView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemList= ArrayList()
        initState()
    }


    override fun showProgressBar(active: Boolean) {
        if(pageToLoad==1){
            progress_bar_view.visibility = if (active) View.VISIBLE else View.GONE
            rv_list.visibility = if (active) View.GONE else View.VISIBLE
        }
    }

    override fun showEmpty(message: String?) {
        if (isAdded) {
            setEmptyRvList()
            emptyViewHolder.setMessage(message ?: getString(R.string.movie_cant_be_found))
        }
    }

    protected fun setEmptyRvList() {
        rv_list.visibility = View.GONE
        emptyViewHolder.show()
    }


    override fun onUnknownError(code: Int, errorMessage: String?) {
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()

                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    emptyViewHolder.setMessage(getString(R.string.error_limit_request))
                    return
                }
                if (code == ApiConfig.HTTP_UNPROCESSABLE_ENTITY) {
                    emptyViewHolder.setMessage(getString(R.string.error_empty_query))
                    return
                }
                emptyViewHolder.setMessage(errorMessage)
            } else {
                adapter?.notifyDataSetChanged()
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(context, R.string.error_limit_request, Toast.LENGTH_LONG).show()
                    return
                }
                if (code == ApiConfig.HTTP_UNPROCESSABLE_ENTITY) {
                    Toast.makeText(context, R.string.error_empty_query, Toast.LENGTH_LONG).show()
                    return
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onTimeout() {
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()
                emptyViewHolder.showOnTimeout()
            } else {
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, R.string.timeout_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onNetworkError() {
        if (isAdded) {
            if (pageToLoad == 1) {
                setEmptyRvList()
                emptyViewHolder.showOnNetworkError()
            } else {
                adapter?.notifyDataSetChanged()
                Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show()
            }
        }
    }

}