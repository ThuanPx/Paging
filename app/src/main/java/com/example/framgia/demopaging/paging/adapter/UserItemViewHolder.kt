package com.example.framgia.demopaging.paging.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.framgia.demopaging.R
import com.example.framgia.demopaging.paging.model.User
import kotlinx.android.synthetic.main.item_paging_user.view.*

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class UserItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: User?) {
        user?.let {
            itemView.UserName.text = user.login
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(itemView.UserAvatar)
            itemView.siteAdminIcon.visibility = if (user.siteAdmin) View.VISIBLE else View.GONE
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_paging_user, parent, false)
            return UserItemViewHolder(view)
        }
    }
}
