package com.example.framgia.demopaging.paging.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.framgia.demopaging.paging.api.GithubService
import com.example.framgia.demopaging.paging.model.User
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class UsersDataSourceFactory(private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Long, User>() {

    val userDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, User> {
        val userDataSource = UsersDataSource(githubService, compositeDisposable)
        userDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }

}