package com.example.framgia.demopaging.paging.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.example.framgia.demopaging.paging.api.GithubService
import com.example.framgia.demopaging.paging.model.User
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Hyperion on 19/07/2018.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class UsersDataSource(private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<Long, User>() {

    val isLoading = MutableLiveData<Boolean>()
    val isRefresh = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        isLoading.postValue(true)
        isRefresh.postValue(true)
        compositeDisposable.add(githubService.getUsers(1, params.requestedLoadSize)
            .subscribe({
                isLoading.postValue(false)
                isRefresh.postValue(false)
                callback.onResult(it)
            }, {
                isLoading.postValue(false)
                isRefresh.postValue(false)
            }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        isLoading.postValue(true)
        compositeDisposable.add(
            githubService.getUsers(params.key, params.requestedLoadSize)
                .subscribe({ users ->
                    isLoading.postValue(false)
                    callback.onResult(users)
                }, {
                    isLoading.postValue(false)
                }))

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
    }

    override fun getKey(item: User): Long {
        return item.id
    }


}
