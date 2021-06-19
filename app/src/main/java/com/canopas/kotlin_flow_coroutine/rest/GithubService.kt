package com.canopas.kotlin_flow_coroutine.rest

import androidx.lifecycle.LiveData
import com.canopas.kotlin_flow_coroutine.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String): Response<SearchResponse>

}