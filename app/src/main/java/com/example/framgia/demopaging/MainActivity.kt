package com.example.framgia.demopaging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.framgia.demopaging.paging.adapter.UserAdapter
import com.example.framgia.demopaging.paging.model.User
import com.hyperion.helpcode.paging.PagingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val viewModel by lazy { ViewModelProviders.of(this).get(PagingViewModel::class.java) }
    private val userAdapter by lazy {
        UserAdapter {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        srPaging.setOnRefreshListener { viewModel.reset() }
        initAdapter()
    }

    private fun initAdapter() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
        usersRecyclerView.adapter = userAdapter
        viewModel.userList.observe(this, Observer<PagedList<User>> { userAdapter.submitList(it) })
        viewModel.getLoading().observe(this,
            Observer<Boolean> {  userAdapter.setLoading(it) })
        viewModel.getRefreshState().observe(this, Observer<Boolean> {
            srPaging.isRefreshing = it?:false
        })
    }
}
