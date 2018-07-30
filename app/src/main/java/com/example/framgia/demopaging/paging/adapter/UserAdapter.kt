package com.example.framgia.demopaging.paging.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.framgia.demopaging.R
import com.example.framgia.demopaging.paging.model.User

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class UserAdapter(
    private val retryCallback: () -> Unit) : PagedListAdapter<User, RecyclerView.ViewHolder>(
    UserDiffCallback) {

    private var isLoading = false

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_paging_user -> UserItemViewHolder.create(p0)
            R.layout.item_paging_loading -> NetworkStateItemViewHolder.create(p0)
            else -> throw IllegalAccessException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_paging_user -> (holder as? UserItemViewHolder)?.bindTo(getItem(position))
            R.layout.item_paging_loading -> (holder as? NetworkStateItemViewHolder)?.bindTo(
                isLoading)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == itemCount - 1) {
            R.layout.item_paging_loading
        } else {
            R.layout.item_paging_user
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoading) 1 else 0
    }

    fun setLoading(isLoading: Boolean?) {
        isLoading?.let {
            currentList?.isNotEmpty()?.let {
                val previousState = this.isLoading
                this.isLoading = isLoading
                if (previousState != isLoading) {
                    if (!isLoading) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                }
            }

            notifyItemInserted(super.getItemCount())
        }
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}
