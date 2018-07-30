package com.hyperion.helpcode.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.framgia.demopaging.paging.api.GithubService
import com.example.framgia.demopaging.paging.data.UsersDataSource
import com.example.framgia.demopaging.paging.data.UsersDataSourceFactory
import com.example.framgia.demopaging.paging.model.User
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class PagingViewModel : ViewModel() {

    var userList: LiveData<PagedList<User>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 5

    private val sourceFactory: UsersDataSourceFactory

    init {
        sourceFactory = UsersDataSourceFactory(GithubService.getService(), compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        userList = LivePagedListBuilder<Long, User>(sourceFactory, config).build()
    }

    fun getLoading() = Transformations.switchMap<UsersDataSource, Boolean>(
        sourceFactory.userDataSourceLiveData) { it.isLoading }

    fun getRefreshState() = Transformations.switchMap<UsersDataSource, Boolean>(
        sourceFactory.userDataSourceLiveData) { it.isRefresh }

    fun reset() {
        sourceFactory.userDataSourceLiveData.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
