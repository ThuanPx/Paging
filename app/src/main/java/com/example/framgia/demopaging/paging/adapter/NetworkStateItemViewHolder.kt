package com.example.framgia.demopaging.paging.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framgia.demopaging.R
import kotlinx.android.synthetic.main.item_paging_loading.view.*

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(isLoading: Boolean) {
        itemView.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paging_loading,
                parent, false)
            return NetworkStateItemViewHolder(view)
        }
    }
}
