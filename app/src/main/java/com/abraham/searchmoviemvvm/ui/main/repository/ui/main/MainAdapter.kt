package com.abraham.searchmoviemvvm.ui.main.repository.ui.main

import android.support.v4.content.ContextCompat.getColor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abraham.searchmoviemvvm.R
import com.abraham.searchmoviemvvm.ui.main.repository.model.ResultsItem
import com.abraham.searchmoviemvvm.ui.main.repository.ui.Const.MOVIE_THUMBNAIL_BASE_URL_EXTRA_SMALL
import com.abraham.searchmoviemvvm.ui.main.repository.util.DateFormater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Abraham on 11/09/2018.
 */
class MainAdapter internal constructor(private val mItemList: List<ResultsItem>?, private val mListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ResultViewHolder(inflater.inflate(R.layout.item_search_result, viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val item = mItemList?.get(i)

        val resultViewHolder = viewHolder as ResultViewHolder
        item?.let { resultViewHolder.bindData(it) }
        resultViewHolder.setOnClickListener(View.OnClickListener { mListener.onItemClicked(resultViewHolder.itemView, item, resultViewHolder.adapterPosition) })


    }

    override fun getItemCount(): Int {
        return mItemList?.size!!
    }


    internal inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivMovie: ImageView
        private val tvMovie: TextView
        private val tvDesc: TextView
        private val tvReleaseDate: TextView

        init {
            ivMovie = itemView.findViewById(R.id.iv_movie_thumbnail)
            tvMovie = itemView.findViewById(R.id.tv_title_movie)
            tvDesc = itemView.findViewById(R.id.tv_desc_movie)
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date_movie)
        }

        fun bindData(item: ResultsItem) {
            tvMovie.text = item.title
            Glide.with(itemView.context)
                    .load(String.format(MOVIE_THUMBNAIL_BASE_URL_EXTRA_SMALL, item.posterPath))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .into(ivMovie)
            tvDesc.text = item.overview

            val releaseDate = item.releaseDate?.let { DateFormater.changeDateFormat("yyyy-MM-dd", item.releaseDate, "EEEE,  MMM dd, yyyy")}
            tvReleaseDate.text = releaseDate
        }

        fun setOnClickListener(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(view: View, data: Any?, position: Int)
    }
}
