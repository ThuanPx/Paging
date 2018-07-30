package com.example.framgia.demopaging.paging.api

import com.example.framgia.demopaging.paging.model.User
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Ahmed Abd-Elmeged on 2/20/2018.
 */
interface GithubService {

    @GET("/users")
    fun getUsers(@Query("since") userId: Long, @Query("per_page") perPage: Int): Single<List<User>>

    companion object {
        fun getService(): GithubService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(GithubService::class.java)
        }
    }

}